package com.ifchange.regressiontest.service.impl;

import com.ifchange.regressiontest.dao.TestDatasetTypesInfoMapper;
import com.ifchange.regressiontest.model.TestDatasetTypesInfo;
import com.ifchange.regressiontest.service.ITestDataSetTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: TestDataSetTypesServiceImpl
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 15:45 2019/3/20
 */
@Service("iTestDataSetTypesService")
public class TestDataSetTypesServiceImpl implements ITestDataSetTypesService {
    @Autowired
    TestDatasetTypesInfoMapper testDatasetTypesInfoMapper;

    @Override
    public int createTestDataSetType(TestDatasetTypesInfo testDatasetTypesInfo) {
        return testDatasetTypesInfoMapper.insertSelective(testDatasetTypesInfo);
    }

    @Override
    public int deleteTestDataSetType(Integer id) {
        return testDatasetTypesInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByTypeIdList(List ids) {
        return testDatasetTypesInfoMapper.deleteByPrimaryKeyList(ids);
    }

    @Override
    public int updateTestDataSetType(TestDatasetTypesInfo testDatasetTypesInfo) {
        return testDatasetTypesInfoMapper.updateByPrimaryKeySelective(testDatasetTypesInfo);
    }

    @Override
    public List<TestDatasetTypesInfo> getTestDataSetType(TestDatasetTypesInfo testDatasetTypesInfo) {
        return testDatasetTypesInfoMapper.selectSelective(testDatasetTypesInfo);
    }

    @Override
    public TestDatasetTypesInfo getTestDataSetTypeById(Integer id) {
        return testDatasetTypesInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public TestDatasetTypesInfo getTypeAndSetByTypeId(Integer id) {
        return testDatasetTypesInfoMapper.selectTypeAndSetByTypeId(id);
    }

    @Override
    public TestDatasetTypesInfo getTypeByName(String name) {
        return testDatasetTypesInfoMapper.selectByName(name);
    }

    @Override
    public List<TestDatasetTypesInfo> getTypeAll() {
        return testDatasetTypesInfoMapper.selectAll();
    }

    @Override
    public List<TestDatasetTypesInfo> getTypeByIdList(List ids) {
        return testDatasetTypesInfoMapper.selectByPrimaryKeyList(ids);
    }

    @Override
    public TestDatasetTypesInfo getTypeAndFormatTemplateByTypeId(Integer id) {
        return testDatasetTypesInfoMapper.selectTypeAndFormatTemplateByTypeId(id);
    }
}
