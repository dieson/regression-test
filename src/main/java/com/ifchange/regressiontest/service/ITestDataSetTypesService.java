package com.ifchange.regressiontest.service;

import com.ifchange.regressiontest.model.TestDatasetTypesInfo;

import java.util.List;

/**
 * @ClassName: ITestDataSetTypesService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 14:46 2019/3/20
 */
public interface ITestDataSetTypesService {
    int createTestDataSetType(TestDatasetTypesInfo testDatasetTypesInfo);

    int deleteTestDataSetType(Integer id);

    int deleteByTypeIdList(List ids);

    int updateTestDataSetType(TestDatasetTypesInfo testDatasetTypesInfo);

    List<TestDatasetTypesInfo> getTestDataSetType(TestDatasetTypesInfo testDatasetTypesInfo);

    TestDatasetTypesInfo getTestDataSetTypeById(Integer id);

    TestDatasetTypesInfo getTypeAndSetByTypeId(Integer id);

    TestDatasetTypesInfo getTypeByName(String name);

    List<TestDatasetTypesInfo> getTypeAll();

    List<TestDatasetTypesInfo> getTypeByIdList(List ids);

    TestDatasetTypesInfo getTypeAndFormatTemplateByTypeId(Integer id);
}
