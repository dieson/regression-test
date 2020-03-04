package com.ifchange.regressiontest.service;

import com.ifchange.regressiontest.model.TestDatasetUploadFormatTemplates;

import java.util.List;

/**
 * @ClassName: ITestDataSetUploadFormatTemplatesService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 16:02 2019/3/25
 */
public interface ITestDataSetUploadFormatTemplatesService {
    int createTestDataSetUploadFormatTemplates(TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates);

    int deleteTestDataSetUploadFormatTemplates(Integer id);

    int deleteByPrimaryKeyList(List ids);

    int updateTestDataSetUploadFormatTemplates(TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates);

    TestDatasetUploadFormatTemplates getTestDataSetUploadFormatTemplates(TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates);

    TestDatasetUploadFormatTemplates getTestDataSetUploadFormatTemplatesById(Integer id);

    List<TestDatasetUploadFormatTemplates> getByPrimaryKeyList(List ids);

    TestDatasetUploadFormatTemplates getUploadTemplatesAndTypeByTemplatesId(Integer id);

    TestDatasetUploadFormatTemplates getTestDataSetUploadFormatTemplatesByTypeId(Integer datasetTypeId);

    List<TestDatasetUploadFormatTemplates> getUploadTemplatesAndTypeSelective(TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates);

    List<TestDatasetUploadFormatTemplates> getAll();
}
