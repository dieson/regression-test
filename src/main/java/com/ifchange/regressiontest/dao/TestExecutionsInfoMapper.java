package com.ifchange.regressiontest.dao;


import com.ifchange.regressiontest.model.TestExecutionsInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestExecutionsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKeyList(List ids);

    int insert(TestExecutionsInfo record);

    int insertSelective(TestExecutionsInfo record);

    TestExecutionsInfo selectByPrimaryKey(Integer id);

    List<TestExecutionsInfo> selectByPrimaryKeyList(List ids);

    TestExecutionsInfo selectSelective(TestExecutionsInfo record);

    TestExecutionsInfo selectExecutionAndSetByExecutionId(Integer id);

    List<TestExecutionsInfo> selectExecutionAndSetSelective(TestExecutionsInfo record);

    TestExecutionsInfo selectExecutionByName(String name);

    List<TestExecutionsInfo> selectAll();

    int updateByPrimaryKeySelective(TestExecutionsInfo record);

    int updateByPrimaryKey(TestExecutionsInfo record);
}