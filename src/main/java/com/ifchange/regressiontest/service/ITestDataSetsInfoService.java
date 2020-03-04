package com.ifchange.regressiontest.service;

import com.ifchange.regressiontest.model.TestDatasetsInfo;

import java.util.List;

/**
 * @ClassName: ITestDataSetsInfoService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 18:36 2019/3/21
 */
public interface ITestDataSetsInfoService {
    int createTestDataSet(TestDatasetsInfo testDatasetsInfo);

    int deleteTestDataSet(Integer id);

    int deleteSetByPrimaryKeyList(List ids);

    int updateTestDataSet(TestDatasetsInfo testDatasetsInfo);

    List<TestDatasetsInfo> getSetByPrimaryKeyList(List ids);

    List<TestDatasetsInfo> getTestDataSet(TestDatasetsInfo testDatasetsInfo);

    List<TestDatasetsInfo> getSetAndTypeSelective(TestDatasetsInfo testDatasetsInfo);

    TestDatasetsInfo getTestDataSetById(Integer id);

    TestDatasetsInfo getTestDataSetByTypeId(Integer datasetTypeId);

    TestDatasetsInfo getSetAndTypeBySetId(Integer id);

    TestDatasetsInfo getSetAndTestcaseBySetId(Integer id);

    TestDatasetsInfo getSetAndExecutionBySetId(Integer id);

    TestDatasetsInfo getByName(String name);

    List<TestDatasetsInfo> getAll();
}
