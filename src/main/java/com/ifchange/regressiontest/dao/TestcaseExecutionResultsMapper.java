package com.ifchange.regressiontest.dao;


import com.ifchange.regressiontest.model.TestcaseExecutionResults;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestcaseExecutionResultsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestcaseExecutionResults record);

    int insertSelective(TestcaseExecutionResults record);

    TestcaseExecutionResults selectByPrimaryKey(Integer id);

    TestcaseExecutionResults selectSelective(TestcaseExecutionResults record);

    TestcaseExecutionResults selectExecutionResultAndTestcaseById(Integer id);

    TestcaseExecutionResults selectExecutionResultAndExecutionById(Integer id);

    List<TestcaseExecutionResults> selectExecutionResultAndExecutionAndTestcaseSelective(TestcaseExecutionResults record);

    List<TestcaseExecutionResults> selectExecutionResultAndExecutionAndTestcaseById(Integer id);

    int updateByPrimaryKeySelective(TestcaseExecutionResults record);

    int updateByPrimaryKey(TestcaseExecutionResults record);
}