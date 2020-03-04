package com.ifchange.regressiontest.ServiceTest;

import com.ifchange.regressiontest.dao.TestDatasetsInfoMapper;
import com.ifchange.regressiontest.model.TestDatasetsInfo;
import com.ifchange.regressiontest.service.ITestDataSetsInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName: TestDataSetsInfoService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 18:49 2019/3/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDataSetsInfoService {
    @Autowired
    ITestDataSetsInfoService iTestDataSetsInfoService;

    @Autowired
    TestDatasetsInfoMapper testDatasetsInfoMapper;

    @Test
    public void testSelectSetInfoAndType() {
        TestDatasetsInfo testDatasetsInfo = testDatasetsInfoMapper.selectSetAndTypeBySetId(3);
        System.out.println(testDatasetsInfo.toString());
        System.out.println(testDatasetsInfo.getTestDatasetTypesInfo());
    }


    @Test
    public void testGetSetAndExecutionBySetId() {
        TestDatasetsInfo testDatasetsInfo = iTestDataSetsInfoService.getSetAndExecutionBySetId(3);
//        TestDatasetsInfo testDatasetsInfo = testDatasetsInfoMapper.selectSetAndExecutionBySetId(3);
        System.out.println(testDatasetsInfo.toString());
        System.out.println(testDatasetsInfo.getTestExecutionsInfoList());
    }


    @Test
    public void testCreate() {
        TestDatasetsInfo testDatasetsInfo = new TestDatasetsInfo();
        testDatasetsInfo.setName("test");
        testDatasetsInfo.setDesc("测试");
        testDatasetsInfo.setDatasetTypeId(4);
        testDatasetsInfo.setTestCaseCount(20);
        testDatasetsInfo.setCreatedBy("test");
        int res = iTestDataSetsInfoService.createTestDataSet(testDatasetsInfo);
        System.out.println(res);
    }

    @Test
    public void testUpdate() {
        TestDatasetsInfo testDatasetsInfo = new TestDatasetsInfo();
        testDatasetsInfo.setId(1);
        testDatasetsInfo.setName("test1");
        testDatasetsInfo.setDesc("测试1");
        testDatasetsInfo.setDatasetTypeId(4);
        testDatasetsInfo.setTestCaseCount(30);
        testDatasetsInfo.setCreatedBy("test1");
        int res = iTestDataSetsInfoService.updateTestDataSet(testDatasetsInfo);
        System.out.println(res);
    }

    @Test
    public void testSelectByPrimaryKey() {
        TestDatasetsInfo res = iTestDataSetsInfoService.getTestDataSetById(3);
        System.out.println(res.toString());
    }

    @Test
    public void testSelective() {
        TestDatasetsInfo testDatasetsInfo = new TestDatasetsInfo();
        testDatasetsInfo.setId(1);
        testDatasetsInfo.setName("test1");
        testDatasetsInfo.setDesc("测试1");
        testDatasetsInfo.setDatasetTypeId(4);
        testDatasetsInfo.setTestCaseCount(30);
        testDatasetsInfo.setCreatedBy("test1");
        List<TestDatasetsInfo> res = iTestDataSetsInfoService.getTestDataSet(testDatasetsInfo);
        System.out.println(res);
    }

    @Test
    public void testDelete() {
        int res = iTestDataSetsInfoService.deleteTestDataSet(1);
        System.out.println(res);
    }
}
