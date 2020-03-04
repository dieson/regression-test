package com.ifchange.regressiontest.dao;


import com.ifchange.regressiontest.model.TestDatasetTypesInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDatasetTypesInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKeyList(List ids);

    int insert(TestDatasetTypesInfo record);

    int insertSelective(TestDatasetTypesInfo record);

    TestDatasetTypesInfo selectByPrimaryKey(Integer id);

    List<TestDatasetTypesInfo> selectSelective(TestDatasetTypesInfo record);

    TestDatasetTypesInfo selectTypeAndSetByTypeId(Integer id);

    TestDatasetTypesInfo selectByName(String name);

    List<TestDatasetTypesInfo> selectAll();

    List<TestDatasetTypesInfo> selectByPrimaryKeyList(List ids);

    TestDatasetTypesInfo selectTypeAndFormatTemplateByTypeId(Integer id);

    int updateByPrimaryKeySelective(TestDatasetTypesInfo record);

    int updateByPrimaryKey(TestDatasetTypesInfo record);
}