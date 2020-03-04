package com.ifchange.regressiontest.dao;


import com.ifchange.regressiontest.model.TestDatasetUploadFormatTemplates;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDatasetUploadFormatTemplatesMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKeyList(List ids);

    int insert(TestDatasetUploadFormatTemplates record);

    int insertSelective(TestDatasetUploadFormatTemplates record);

    TestDatasetUploadFormatTemplates selectByPrimaryKey(Integer id);

    List<TestDatasetUploadFormatTemplates> selectByPrimaryKeyList(List ids);

    TestDatasetUploadFormatTemplates selectSelective(TestDatasetUploadFormatTemplates record);

    TestDatasetUploadFormatTemplates selectUploadTemplatesAndTypeByTemplatesId(Integer id);

    List<TestDatasetUploadFormatTemplates> selectUploadTemplatesAndTypeSelective(TestDatasetUploadFormatTemplates record);

    TestDatasetUploadFormatTemplates selectByTypeId(Integer datasetTypeId);

    List<TestDatasetUploadFormatTemplates> selectAll();

    int updateByPrimaryKeySelective(TestDatasetUploadFormatTemplates record);

    int updateByPrimaryKey(TestDatasetUploadFormatTemplates record);
}