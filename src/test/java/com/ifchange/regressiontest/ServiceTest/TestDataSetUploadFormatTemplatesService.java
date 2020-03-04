package com.ifchange.regressiontest.ServiceTest;

import com.ifchange.regressiontest.dao.TestDatasetUploadFormatTemplatesMapper;
import com.ifchange.regressiontest.model.TestDatasetUploadFormatTemplates;
import com.ifchange.regressiontest.service.ITestDataSetUploadFormatTemplatesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: TestDataSetUploadFormatTemplatesService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 11:19 2019/4/1
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDataSetUploadFormatTemplatesService {
    @Autowired
    ITestDataSetUploadFormatTemplatesService iTestDataSetUploadFormatTemplatesService;

    @Autowired
    TestDatasetUploadFormatTemplatesMapper testDatasetUploadFormatTemplatesMapper;

    @Test
    public void testCreate(){
        TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates = new TestDatasetUploadFormatTemplates();
        testDatasetUploadFormatTemplates.setDatasetTypeId(4);
        testDatasetUploadFormatTemplates.setRequestColumName("test");
        testDatasetUploadFormatTemplates.setRequestColumDataType("测试");
//        int i = iTestDataSetUploadFormatTemplatesService.createTestDataSetUploadFormatTemplates(testDatasetUploadFormatTemplates);
//        int i = testDatasetUploadFormatTemplatesMapper.insertSelective(testDatasetUploadFormatTemplates);
//        System.out.println(i);
    }

    @Test
    public void testGetUploadTemplatesAndTypeByTemplatesId(){
        TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates = iTestDataSetUploadFormatTemplatesService.getUploadTemplatesAndTypeByTemplatesId(5);

        System.out.println(testDatasetUploadFormatTemplates.toString());
        System.out.println(testDatasetUploadFormatTemplates.getTestDatasetTypesInfo());

    }

}
