package com.ifchange.regressiontest.service.impl;

import com.ifchange.regressiontest.dao.TestDatasetsInfoMapper;
import com.ifchange.regressiontest.dao.TestExecutionsInfoMapper;
import com.ifchange.regressiontest.dao.TestcaseExecutionResultsMapper;
import com.ifchange.regressiontest.model.*;
import com.ifchange.regressiontest.service.ITaskService;
import com.ifchange.regressiontest.util.FastJsonDiff;
import com.ifchange.regressiontest.util.HttpClientUtil;
import com.ifchange.regressiontest.util.JsonUtil;
import com.ifchange.regressiontest.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @ClassName: TaskServiceImpl
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 14:05 2019/4/10
 */
@Service("iTaskService")
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private TestExecutionsInfoMapper testExecutionsInfoMapper;
    @Autowired
    private TestDatasetsInfoMapper testDatasetsInfoMapper;
    @Autowired
    private TestcaseExecutionResultsMapper testcaseExecutionResultsMapper;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Async
    public void executionTestcase(int executionId) {
        TestExecutionsInfo testExecutionsInfo = testExecutionsInfoMapper.selectByPrimaryKey(executionId);
        int setId = testExecutionsInfo.getTestDatasetId();

        TestDatasetsInfo setAndTestcase = testDatasetsInfoMapper.selectSetAndTestcaseBySetId(setId);
        List<Testcases> testcaseList = setAndTestcase.getTestcasesList();

        TestDatasetsInfo setAndType = testDatasetsInfoMapper.selectSetAndTypeBySetId(setId);
        TestDatasetTypesInfo testDatasetTypesInfo = setAndType.getTestDatasetTypesInfo();
        String serverAddress = testDatasetTypesInfo.getDefaultServiceAddress();

        int finishedCount = 0;
        int passedCount = 0;
        for (Testcases testcase : testcaseList) {
            finishedCount++;
            try {
                // 写入testcaseExecutionResults
                String result = HttpClientUtil.postTimeOutAndTryCount(serverAddress, testcase.getData(), 3000, 2);
                boolean pass = FastJsonDiff.compareJson(JsonUtil.strToJson(testcase.getExpectData()), JsonUtil.strToJson(result));

                TestcaseExecutionResults testcaseExecutionResults = new TestcaseExecutionResults();
                testcaseExecutionResults.setTestExecutionId(executionId);
                testcaseExecutionResults.setTestcaseId(testcase.getId());

                String status = StringUtil.isEmpty(result) ? "failure" : "success";
                testcaseExecutionResults.setExecutionStatus(status);

                testcaseExecutionResults.setResultData(result);
                testcaseExecutionResults.setPassed(pass);
                /*
                 * 添加correctness/correctness_score字段
                 */
                testcaseExecutionResultsMapper.insertSelective(testcaseExecutionResults);

                // 更新testExecutionsInfo
                passedCount = pass ? passedCount + 1 : passedCount;
                String executionStatus = finishedCount < testcaseList.size() ? "执行中" : "执行完成";
                /*
                添加correctness_score、adjusted_correctness_score、correctness_distribution字段
                 */
                testExecutionsInfo.setFinishedTestcaseCount(finishedCount);
                testExecutionsInfo.setTestExecutionStatus(executionStatus);
                testExecutionsInfo.setPassedTestcaseCount(passedCount);
                testExecutionsInfoMapper.updateByPrimaryKeySelective(testExecutionsInfo);
            } catch (UnknownHostException e) {
                logger.error("请求失败,找不到映射名称或服务： ", e);
                // 写入testcaseExecutionResults
                TestcaseExecutionResults testcaseExecutionResults = new TestcaseExecutionResults();
                testcaseExecutionResults.setTestExecutionId(executionId);
                testcaseExecutionResults.setTestcaseId(testcase.getId());
                testcaseExecutionResults.setExecutionStatus("失败");
                testcaseExecutionResults.setPassed(false);
                testcaseExecutionResultsMapper.insertSelective(testcaseExecutionResults);

                // 更新testExecutionsInfo
                String executionStatus = finishedCount < testcaseList.size() ? "执行中" : "执行完成";
                /*
                添加correctness_score、adjusted_correctness_score、correctness_distribution字段
                 */
                testExecutionsInfo.setFinishedTestcaseCount(finishedCount);
                testExecutionsInfo.setTestExecutionStatus(executionStatus);
                testExecutionsInfoMapper.updateByPrimaryKeySelective(testExecutionsInfo);
            } catch (IOException e) {
                logger.error("请求失败,TestCaseId(" + testcase.getId() + ")： ", e);
            }
        }
    }


}
