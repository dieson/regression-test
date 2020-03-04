package com.ifchange.regressiontest.service;

import com.ifchange.regressiontest.model.TestcaseExecutionResults;

import java.util.List;

/**
 * @ClassName: ITestCaseExecutionResultsService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 10:24 2019/3/25
 */
public interface ITestCaseExecutionResultsService {
    int addTestCaseExecutionResults(TestcaseExecutionResults testcaseExecutionResults);

    int deleteTestCaseExecutionResults(Integer id);

    int updateTestCaseExecutionResults(TestcaseExecutionResults testcaseExecutionResults);

    TestcaseExecutionResults getTestCaseExecutionResults(TestcaseExecutionResults testcaseExecutionResults);

    TestcaseExecutionResults getTestCaseExecutionResults(Integer id);

    TestcaseExecutionResults getExecutionResultAndTestcaseById(Integer id);

    TestcaseExecutionResults getExecutionResultAndExecutionById(Integer id);

    List<TestcaseExecutionResults> getExecutionResultAndExecutionAndTestcaseSelective(TestcaseExecutionResults record);

    List<TestcaseExecutionResults> getExecutionResultAndExecutionAndTestcaseById(Integer id);
}
