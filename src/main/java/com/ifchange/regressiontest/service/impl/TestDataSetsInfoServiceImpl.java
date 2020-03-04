package com.ifchange.regressiontest.service.impl;

import com.ifchange.regressiontest.dao.TestDatasetsInfoMapper;
import com.ifchange.regressiontest.model.TestDatasetsInfo;
import com.ifchange.regressiontest.service.ITestDataSetsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: TestDataSetsInfoServiceImpl
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 18:38 2019/3/21
 */
@Service("iTestDataSetsInfoService")
public class TestDataSetsInfoServiceImpl implements ITestDataSetsInfoService {
    @Autowired
    TestDatasetsInfoMapper testDatasetsInfoMapper;

    @Override
    public int createTestDataSet(TestDatasetsInfo testDatasetsInfo) {
        return testDatasetsInfoMapper.insertSelective(testDatasetsInfo);
    }

    @Override
    public int deleteTestDataSet(Integer id) {
        return testDatasetsInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteSetByPrimaryKeyList(List ids) {
        return testDatasetsInfoMapper.deleteByPrimaryKeyList(ids);
    }

    @Override
    public int updateTestDataSet(TestDatasetsInfo testDatasetsInfo) {
        return testDatasetsInfoMapper.updateByPrimaryKeySelective(testDatasetsInfo);
    }

    @Override
    public List<TestDatasetsInfo> getSetByPrimaryKeyList(List ids) {
        return testDatasetsInfoMapper.selectByPrimaryKeyList(ids);
    }

    @Override
    public List<TestDatasetsInfo> getTestDataSet(TestDatasetsInfo testDatasetsInfo) {
        return testDatasetsInfoMapper.selectSelective(testDatasetsInfo);
    }

    @Override
    public List<TestDatasetsInfo> getSetAndTypeSelective(TestDatasetsInfo testDatasetsInfo) {
        return testDatasetsInfoMapper.selectSetAndTypeSelective(testDatasetsInfo);
    }

    @Override
    public TestDatasetsInfo getTestDataSetById(Integer id) {
        return testDatasetsInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public TestDatasetsInfo getTestDataSetByTypeId(Integer datasetTypeId) {
        return testDatasetsInfoMapper.selectByTypeId(datasetTypeId);
    }

    @Override
    public TestDatasetsInfo getSetAndTypeBySetId(Integer id) {
        return testDatasetsInfoMapper.selectSetAndTypeBySetId(id);
    }

    @Override
    public TestDatasetsInfo getSetAndTestcaseBySetId(Integer id) {
        return testDatasetsInfoMapper.selectSetAndTestcaseBySetId(id);
    }

    @Override
    public TestDatasetsInfo getSetAndExecutionBySetId(Integer id) {
        return testDatasetsInfoMapper.selectSetAndExecutionBySetId(id);
    }

    @Override
    public TestDatasetsInfo getByName(String name) {
        return testDatasetsInfoMapper.selectByName(name);
    }

    @Override
    public List<TestDatasetsInfo> getAll() {
        return testDatasetsInfoMapper.selectAll();
    }
}
