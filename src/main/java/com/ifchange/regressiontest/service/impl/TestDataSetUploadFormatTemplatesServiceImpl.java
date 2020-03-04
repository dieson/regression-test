package com.ifchange.regressiontest.service.impl;

import com.ifchange.regressiontest.dao.TestDatasetUploadFormatTemplatesMapper;
import com.ifchange.regressiontest.model.TestDatasetUploadFormatTemplates;
import com.ifchange.regressiontest.service.ITestDataSetUploadFormatTemplatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: TestDataSetUploadFormatTemplatesServiceImpl
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 16:05 2019/3/25
 */
@Service("iTestDataSetUploadFormatTemplatesService")
public class TestDataSetUploadFormatTemplatesServiceImpl implements ITestDataSetUploadFormatTemplatesService {
    @Autowired
    TestDatasetUploadFormatTemplatesMapper testDatasetUploadFormatTemplatesMapper;

    @Override
    public int createTestDataSetUploadFormatTemplates(TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates) {
        return testDatasetUploadFormatTemplatesMapper.insertSelective(testDatasetUploadFormatTemplates);
    }

    @Override
    public int deleteTestDataSetUploadFormatTemplates(Integer id) {
        return testDatasetUploadFormatTemplatesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKeyList(List ids) {
        return testDatasetUploadFormatTemplatesMapper.deleteByPrimaryKeyList(ids);
    }

    @Override
    public int updateTestDataSetUploadFormatTemplates(TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates) {
        return testDatasetUploadFormatTemplatesMapper.updateByPrimaryKeySelective(testDatasetUploadFormatTemplates);
    }

    @Override
    public TestDatasetUploadFormatTemplates getTestDataSetUploadFormatTemplates(TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates) {
        return testDatasetUploadFormatTemplatesMapper.selectSelective(testDatasetUploadFormatTemplates);
    }

    @Override
    public TestDatasetUploadFormatTemplates getTestDataSetUploadFormatTemplatesById(Integer id) {
        return testDatasetUploadFormatTemplatesMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TestDatasetUploadFormatTemplates> getByPrimaryKeyList(List ids) {
        return testDatasetUploadFormatTemplatesMapper.selectByPrimaryKeyList(ids);
    }

    @Override
    public TestDatasetUploadFormatTemplates getUploadTemplatesAndTypeByTemplatesId(Integer id) {
        return testDatasetUploadFormatTemplatesMapper.selectUploadTemplatesAndTypeByTemplatesId(id);
    }

    @Override
    public TestDatasetUploadFormatTemplates getTestDataSetUploadFormatTemplatesByTypeId(Integer datasetTypeId) {
        return testDatasetUploadFormatTemplatesMapper.selectByTypeId(datasetTypeId);
    }

    @Override
    public List<TestDatasetUploadFormatTemplates> getUploadTemplatesAndTypeSelective(TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates) {
        return testDatasetUploadFormatTemplatesMapper.selectUploadTemplatesAndTypeSelective(testDatasetUploadFormatTemplates);
    }

    @Override
    public List<TestDatasetUploadFormatTemplates> getAll() {
        return testDatasetUploadFormatTemplatesMapper.selectAll();
    }
}
