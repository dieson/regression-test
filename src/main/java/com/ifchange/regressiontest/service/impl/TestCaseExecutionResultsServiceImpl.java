package com.ifchange.regressiontest.service.impl;

import com.ifchange.regressiontest.dao.TestcaseExecutionResultsMapper;
import com.ifchange.regressiontest.model.TestcaseExecutionResults;
import com.ifchange.regressiontest.service.ITestCaseExecutionResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: TestCaseExecutionResultsServiceImpl
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 10:26 2019/3/25
 */
@Service("iTestCaseExecutionResultsService")
public class TestCaseExecutionResultsServiceImpl implements ITestCaseExecutionResultsService {
    @Autowired
    TestcaseExecutionResultsMapper testcaseExecutionResultsMapper;

    @Override
    public int addTestCaseExecutionResults(TestcaseExecutionResults testcaseExecutionResults) {
        return testcaseExecutionResultsMapper.insertSelective(testcaseExecutionResults);
    }

    @Override
    public int deleteTestCaseExecutionResults(Integer id) {
        return testcaseExecutionResultsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateTestCaseExecutionResults(TestcaseExecutionResults testcaseExecutionResults) {
        return testcaseExecutionResultsMapper.updateByPrimaryKeySelective(testcaseExecutionResults);
    }

    @Override
    public TestcaseExecutionResults getTestCaseExecutionResults(TestcaseExecutionResults testcaseExecutionResults) {
        return testcaseExecutionResultsMapper.selectSelective(testcaseExecutionResults);
    }

    @Override
    public TestcaseExecutionResults getTestCaseExecutionResults(Integer id) {
        return testcaseExecutionResultsMapper.selectByPrimaryKey(id);
    }

    @Override
    public TestcaseExecutionResults getExecutionResultAndTestcaseById(Integer id) {
        return testcaseExecutionResultsMapper.selectExecutionResultAndTestcaseById(id);
    }

    @Override
    public TestcaseExecutionResults getExecutionResultAndExecutionById(Integer id) {
        return testcaseExecutionResultsMapper.selectExecutionResultAndExecutionById(id);
    }

    @Override
    public List<TestcaseExecutionResults> getExecutionResultAndExecutionAndTestcaseSelective(TestcaseExecutionResults record) {
        return testcaseExecutionResultsMapper.selectExecutionResultAndExecutionAndTestcaseSelective(record);
    }

    @Override
    public List<TestcaseExecutionResults> getExecutionResultAndExecutionAndTestcaseById(Integer id) {
        return testcaseExecutionResultsMapper.selectExecutionResultAndExecutionAndTestcaseById(id);
    }
}
