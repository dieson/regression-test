package com.ifchange.regressiontest.dao;


import com.ifchange.regressiontest.model.TestcaseServiceInputTemplates;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestcaseServiceInputTemplatesMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKeyList(List ids);

    int insert(TestcaseServiceInputTemplates record);

    int insertSelective(TestcaseServiceInputTemplates record);

    TestcaseServiceInputTemplates selectByPrimaryKey(Integer id);

    TestcaseServiceInputTemplates selectSelective(TestcaseServiceInputTemplates record);

    TestcaseServiceInputTemplates selectTemplatesAndTypeByTemplateId(Integer id);

    List<TestcaseServiceInputTemplates> selectTemplatesAndTypeSelective(TestcaseServiceInputTemplates record);

    List<TestcaseServiceInputTemplates> selectByPrimaryKeyList(List ids);

    List<TestcaseServiceInputTemplates> selectAll();

    TestcaseServiceInputTemplates selectByDataSetTypeId(Integer id);

    int updateByPrimaryKeySelective(TestcaseServiceInputTemplates record);

    int updateByPrimaryKey(TestcaseServiceInputTemplates record);
}