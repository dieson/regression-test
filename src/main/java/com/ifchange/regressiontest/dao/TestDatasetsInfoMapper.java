package com.ifchange.regressiontest.dao;


import com.ifchange.regressiontest.model.TestDatasetsInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDatasetsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKeyList(List ids);

    int insert(TestDatasetsInfo record);

    int insertSelective(TestDatasetsInfo record);

    TestDatasetsInfo selectByPrimaryKey(Integer id);

    TestDatasetsInfo selectByTypeId(Integer datasetTypeId);

    List<TestDatasetsInfo> selectByPrimaryKeyList(List ids);

    List<TestDatasetsInfo> selectSelective(TestDatasetsInfo record);

    List<TestDatasetsInfo> selectSetAndTypeSelective(TestDatasetsInfo record);

    TestDatasetsInfo selectSetAndTypeBySetId(Integer id);

    TestDatasetsInfo selectSetAndTestcaseBySetId(Integer id);

    TestDatasetsInfo selectSetAndExecutionBySetId(Integer id);

    TestDatasetsInfo selectByName(String name);

    List<TestDatasetsInfo> selectAll();

    int updateByPrimaryKeySelective(TestDatasetsInfo record);

    int updateByPrimaryKey(TestDatasetsInfo record);
}