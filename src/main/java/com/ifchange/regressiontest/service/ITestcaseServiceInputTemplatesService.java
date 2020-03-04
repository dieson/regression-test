package com.ifchange.regressiontest.service;

import com.ifchange.regressiontest.model.TestcaseServiceInputTemplates;

import java.util.List;

/**
 * @ClassName: ITestcaseServiceInputTemplatesService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 15:00 2019/3/25
 */
public interface ITestcaseServiceInputTemplatesService {
    int createTestcaseServiceInputTemplates(TestcaseServiceInputTemplates testcaseServiceInputTemplates);

    int deleteTestcaseServiceInputTemplates(Integer id);

    int deleteByPrimaryKeyList(List ids);

    int updateTestcaseServiceInputTemplates(TestcaseServiceInputTemplates testcaseServiceInputTemplates);

    TestcaseServiceInputTemplates getTestcaseServiceInputTemplates(TestcaseServiceInputTemplates testcaseServiceInputTemplates);

    TestcaseServiceInputTemplates getTestcaseServiceInputTemplatesById(Integer id);

    TestcaseServiceInputTemplates getTemplatesAndTypeByTemplateId(Integer id);

    List<TestcaseServiceInputTemplates> getTemplatesAll();

    List<TestcaseServiceInputTemplates> getTemplatesAndTypeSelective(TestcaseServiceInputTemplates testcaseServiceInputTemplates);

    List<TestcaseServiceInputTemplates> getByPrimaryKeyList(List ids);

    TestcaseServiceInputTemplates getInputTemplateByDataSetTypeId(Integer id);
}
