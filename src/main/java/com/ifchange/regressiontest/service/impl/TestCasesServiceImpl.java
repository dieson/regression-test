package com.ifchange.regressiontest.service.impl;

import com.ifchange.regressiontest.dao.TestcasesMapper;
import com.ifchange.regressiontest.model.Testcases;
import com.ifchange.regressiontest.service.ITestCasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: TestCasesServiceImpl
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 14:49 2019/3/25
 */
@Service("iTestCasesService")
public class TestCasesServiceImpl implements ITestCasesService {
    @Autowired
    TestcasesMapper testcasesMapper;

    @Override
    public int createTestCases(Testcases testcases) {
        return testcasesMapper.insertSelective(testcases);
    }

    @Override
    public int deleteTestCases(Integer id) {
        return testcasesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKeyList(List ids) {
        return testcasesMapper.deleteByPrimaryKeyList(ids);
    }

    @Override
    public int updateTestCases(Testcases testcases) {
        return testcasesMapper.updateByPrimaryKeySelective(testcases);
    }

    @Override
    public List<Testcases> getTestCases(Testcases testcases) {
        return testcasesMapper.selectSelective(testcases);
    }

    @Override
    public List<Testcases> getBySetId(Integer id) {
        return testcasesMapper.selectBySetId(id);
    }

    @Override
    public List<Testcases> getTestCaseAndSetSelective(Testcases testcases) {
        return testcasesMapper.selectTestCaseAndSetSelective(testcases);
    }

    @Override
    public List<Testcases> getByPrimaryKeyList(List ids) {
        return testcasesMapper.selectByPrimaryKeyList(ids);
    }

    @Override
    public Testcases getTestCasesById(Integer id) {
        return testcasesMapper.selectByPrimaryKey(id);
    }

    @Override
    public Testcases getTestcaseAndSetInfoByCaseId(Integer id) {
        return testcasesMapper.selectTestcaseAndSetInfoByCaseId(id);
    }

    @Override
    public List<Testcases> getAll() {
        return testcasesMapper.selectAll();
    }
}
