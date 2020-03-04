package com.ifchange.regressiontest.service;

import com.ifchange.regressiontest.model.Testcases;

import java.util.List;

/**
 * @ClassName: ITestCasesService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 14:48 2019/3/25
 */
public interface ITestCasesService {
    int createTestCases(Testcases testcases);

    int deleteTestCases(Integer id);

    int deleteByPrimaryKeyList(List ids);

    int updateTestCases(Testcases testcases);

    List<Testcases> getTestCases(Testcases testcases);

    List<Testcases> getBySetId(Integer id);

    List<Testcases> getTestCaseAndSetSelective(Testcases testcases);

    List<Testcases> getByPrimaryKeyList(List ids);

    Testcases getTestCasesById(Integer id);

    Testcases getTestcaseAndSetInfoByCaseId(Integer id);

    List<Testcases> getAll();
}
