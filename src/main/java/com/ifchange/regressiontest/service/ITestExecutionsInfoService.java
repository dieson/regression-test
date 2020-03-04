package com.ifchange.regressiontest.service;

import com.ifchange.regressiontest.model.TestExecutionsInfo;

import java.util.List;

/**
 * @ClassName: ITestExecutionsInfoService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 14:17 2019/3/25
 */
public interface ITestExecutionsInfoService {
    int createTestExecutionsInfo(TestExecutionsInfo testExecutionsInfo);

    int deleteTestExecutionsInfo(Integer id);

    int deleteByPrimaryKeyList(List ids);

    int updateTestExecutionsInfo(TestExecutionsInfo testExecutionsInfo);

    TestExecutionsInfo getTestExecutionsInfo(TestExecutionsInfo testExecutionsInfo);

    TestExecutionsInfo getTestExecutionsInfoById(Integer id);

    TestExecutionsInfo getExecutionAndSetByExecutionId(Integer id);

    List<TestExecutionsInfo> getExecutionAndSetSelective(TestExecutionsInfo record);

    List<TestExecutionsInfo> getByPrimaryKeyList(List ids);

    TestExecutionsInfo getExecutionByName(String name);

    List<TestExecutionsInfo> getAll();
}
