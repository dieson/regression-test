package com.ifchange.regressiontest.controller;

import com.ifchange.regressiontest.constant.ResponseCode;
import com.ifchange.regressiontest.dto.ServerResponse;
import com.ifchange.regressiontest.model.Page;
import com.ifchange.regressiontest.model.TestExecutionsInfo;
import com.ifchange.regressiontest.model.TestcaseExecutionResults;
import com.ifchange.regressiontest.service.ITestCaseExecutionResultsService;
import com.ifchange.regressiontest.util.JsonUtil;
import com.ifchange.regressiontest.util.ValidataUtil;
import org.apache.catalina.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: TestcaseExecutionResultsController
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 14:21 2019/4/9
 */
@RestController
@RequestMapping("/execution_result")
public class TestcaseExecutionResultsController {
    @Autowired
    private ITestCaseExecutionResultsService iTestCaseExecutionResultsService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ServerResponse getExecutionResults(@Validated @RequestBody Page page, BindingResult result) {
        logger.info("查询Execution Result列表");
        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }

            TestcaseExecutionResults testcaseExecutionResults = JsonUtil.jsonToObject(JsonUtil.objToJson(page.getData()), TestcaseExecutionResults.class);
            List<TestcaseExecutionResults> testcaseExecutionResultsList = iTestCaseExecutionResultsService.getExecutionResultAndExecutionAndTestcaseSelective(testcaseExecutionResults);
            int total = testcaseExecutionResultsList.size();
            Page p = Page.createPage(page.getCurrentPage(), page.getPageSize(), total, testcaseExecutionResultsList);

            if (testcaseExecutionResultsList.size() == 0) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.FOUND_RESULT.getCode(), ResponseCode.FOUND_RESULT.getDesc());
            }

            return ServerResponse.createBySuccess(p);
        } catch (Exception e) {
            logger.error("查询Execution Result列表失败： ", e);
            return ServerResponse.createByErrorMessage("查询Execution Result列表失败");
        }
    }
}
