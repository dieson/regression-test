package com.ifchange.regressiontest.service.impl;

import com.ifchange.regressiontest.dao.TestcaseServiceInputTemplatesMapper;
import com.ifchange.regressiontest.model.TestcaseServiceInputTemplates;
import com.ifchange.regressiontest.service.ITestcaseServiceInputTemplatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: TestcaseServiceInputTemplatesServiceImpl
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 15:04 2019/3/25
 */
@Service("iTestcaseServiceInputTemplatesService")
public class TestcaseServiceInputTemplatesServiceImpl implements ITestcaseServiceInputTemplatesService {
    @Autowired
    TestcaseServiceInputTemplatesMapper testcaseServiceInputTemplatesMapper;

    @Override
    public int createTestcaseServiceInputTemplates(TestcaseServiceInputTemplates testcaseServiceInputTemplates) {
        return testcaseServiceInputTemplatesMapper.insertSelective(testcaseServiceInputTemplates);
    }

    @Override
    public int deleteTestcaseServiceInputTemplates(Integer id) {
        return testcaseServiceInputTemplatesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKeyList(List ids) {
        return testcaseServiceInputTemplatesMapper.deleteByPrimaryKeyList(ids);
    }

    @Override
    public int updateTestcaseServiceInputTemplates(TestcaseServiceInputTemplates testcaseServiceInputTemplates) {
        return testcaseServiceInputTemplatesMapper.updateByPrimaryKeySelective(testcaseServiceInputTemplates);
    }

    @Override
    public TestcaseServiceInputTemplates getTestcaseServiceInputTemplates(TestcaseServiceInputTemplates testcaseServiceInputTemplates) {
        return testcaseServiceInputTemplatesMapper.selectSelective(testcaseServiceInputTemplates);
    }

    @Override
    public TestcaseServiceInputTemplates getTestcaseServiceInputTemplatesById(Integer id) {
        return testcaseServiceInputTemplatesMapper.selectByPrimaryKey(id);
    }

    @Override
    public TestcaseServiceInputTemplates getTemplatesAndTypeByTemplateId(Integer id) {
        return testcaseServiceInputTemplatesMapper.selectTemplatesAndTypeByTemplateId(id);
    }

    @Override
    public List<TestcaseServiceInputTemplates> getTemplatesAll() {
        return testcaseServiceInputTemplatesMapper.selectAll();
    }

    @Override
    public List<TestcaseServiceInputTemplates> getTemplatesAndTypeSelective(TestcaseServiceInputTemplates testcaseServiceInputTemplates) {
        return testcaseServiceInputTemplatesMapper.selectTemplatesAndTypeSelective(testcaseServiceInputTemplates);
    }

    @Override
    public List<TestcaseServiceInputTemplates> getByPrimaryKeyList(List ids) {
        return testcaseServiceInputTemplatesMapper.selectByPrimaryKeyList(ids);
    }

    @Override
    public TestcaseServiceInputTemplates getInputTemplateByDataSetTypeId(Integer id) {
        return testcaseServiceInputTemplatesMapper.selectByDataSetTypeId(id);
    }
}
