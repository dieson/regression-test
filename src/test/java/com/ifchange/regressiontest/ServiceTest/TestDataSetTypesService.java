package com.ifchange.regressiontest.ServiceTest;

import com.ifchange.regressiontest.dao.TestDatasetTypesInfoMapper;
import com.ifchange.regressiontest.model.TestDatasetTypesInfo;
import com.ifchange.regressiontest.service.ITestDataSetTypesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestDataSetTypesService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 15:54 2019/3/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDataSetTypesService {
    @Autowired
    ITestDataSetTypesService iTestDataSetTypesService;
    @Autowired
    TestDatasetTypesInfoMapper testDatasetTypesInfoMapper;

    @Test
    public void testSelectTypeList(){
        List ids = new ArrayList();
        ids.add(14);
        ids.add(15);
        List i = iTestDataSetTypesService.getTypeByIdList(ids);
        System.out.println(i);
    }

    @Test
    public void testDeleteTypeList(){
        List ids = new ArrayList();
        ids.add(12);
        ids.add(13);
        int i = iTestDataSetTypesService.deleteByTypeIdList(ids);
        System.out.println(i);
    }

    @Test
    public void testSelectTypeAndInputTemplate(){
        TestDatasetTypesInfo testDatasetTypesInfo = iTestDataSetTypesService.getTypeAndFormatTemplateByTypeId(4);
        System.out.println(testDatasetTypesInfo.toString());
        System.out.println(testDatasetTypesInfo.getTestDatasetUploadFormatTemplatesList());
    }

    @Test
    public void testSelectTypeInfo(){
        TestDatasetTypesInfo testDatasetTypesInfo = testDatasetTypesInfoMapper.selectTypeAndSetByTypeId(4);
        System.out.println(testDatasetTypesInfo.toString());
        System.out.println(testDatasetTypesInfo.getTestDatasetsInfoList());
    }

    @Test
    public void testCreate() {
        TestDatasetTypesInfo testDatasetTypesInfo = new TestDatasetTypesInfo();
        testDatasetTypesInfo.setName("test");
        testDatasetTypesInfo.setDefaultServiceAddress("127.0.0.1");
        testDatasetTypesInfo.setCreatedBy("admin");
        int i = iTestDataSetTypesService.createTestDataSetType(testDatasetTypesInfo);
        System.out.println(i);
    }

    @Test
    public void testUpdate() {
        TestDatasetTypesInfo testDatasetTypesInfo = new TestDatasetTypesInfo();
        testDatasetTypesInfo.setName("test1");
        testDatasetTypesInfo.setDefaultServiceAddress("127.0.0.2");
        testDatasetTypesInfo.setCreatedBy("admin1");
        testDatasetTypesInfo.setId(2);
        int i = iTestDataSetTypesService.updateTestDataSetType(testDatasetTypesInfo);
        System.out.println(i);
    }

    @Test
    public void testSelect() {
        TestDatasetTypesInfo testDatasetTypesInfo = new TestDatasetTypesInfo();
        testDatasetTypesInfo.setName("test1");
        testDatasetTypesInfo.setDefaultServiceAddress("127.0.0.2");
        testDatasetTypesInfo.setCreatedBy("admin1");
//        TestDatasetTypesInfo i = iTestDataSetTypesService.getTestDataSetType(testDatasetTypesInfo);
        TestDatasetTypesInfo i = iTestDataSetTypesService.getTestDataSetTypeById(4);

        System.out.println(i.toString());
    }

    @Test
    public void testDelete() {
        int i = iTestDataSetTypesService.deleteTestDataSetType(2);
        System.out.println(i);
    }

}
