package com.ifchange.regressiontest.service.impl;

import com.ifchange.regressiontest.dao.TestExecutionsInfoMapper;
import com.ifchange.regressiontest.model.TestExecutionsInfo;
import com.ifchange.regressiontest.service.ITestExecutionsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: TestExecutionsInfoServiceImpl
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 14:19 2019/3/25
 */
@Service("iTestExecutionsInfoService")
public class TestExecutionsInfoServiceImpl implements ITestExecutionsInfoService {
    @Autowired
    TestExecutionsInfoMapper testExecutionsInfoMapper;

    @Override
    public int createTestExecutionsInfo(TestExecutionsInfo testExecutionsInfo) {
        return testExecutionsInfoMapper.insertSelective(testExecutionsInfo);
    }

    @Override
    public int deleteTestExecutionsInfo(Integer id) {
        return testExecutionsInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKeyList(List ids) {
        return testExecutionsInfoMapper.deleteByPrimaryKeyList(ids);
    }

    @Override
    public int updateTestExecutionsInfo(TestExecutionsInfo testExecutionsInfo) {
        return testExecutionsInfoMapper.updateByPrimaryKeySelective(testExecutionsInfo);
    }

    @Override
    public TestExecutionsInfo getTestExecutionsInfo(TestExecutionsInfo testExecutionsInfo) {
        return testExecutionsInfoMapper.selectSelective(testExecutionsInfo);
    }

    @Override
    public TestExecutionsInfo getTestExecutionsInfoById(Integer id) {
        return testExecutionsInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public TestExecutionsInfo getExecutionAndSetByExecutionId(Integer id) {
        return testExecutionsInfoMapper.selectExecutionAndSetByExecutionId(id);
    }

    @Override
    public List<TestExecutionsInfo> getExecutionAndSetSelective(TestExecutionsInfo record) {
        return testExecutionsInfoMapper.selectExecutionAndSetSelective(record);
    }

    @Override
    public List<TestExecutionsInfo> getByPrimaryKeyList(List ids) {
        return testExecutionsInfoMapper.selectByPrimaryKeyList(ids);
    }

    @Override
    public TestExecutionsInfo getExecutionByName(String name) {
        return testExecutionsInfoMapper.selectExecutionByName(name);
    }

    @Override
    public List<TestExecutionsInfo> getAll() {
        return testExecutionsInfoMapper.selectAll();
    }
}
