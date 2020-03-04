package com.ifchange.regressiontest.ServiceTest;

import com.ifchange.regressiontest.model.TestcaseServiceInputTemplates;
import com.ifchange.regressiontest.service.ITestcaseServiceInputTemplatesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: TestcaseServiceInputTemplatesService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 11:01 2019/4/1
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestcaseServiceInputTemplatesService {
    @Autowired
    ITestcaseServiceInputTemplatesService iTestcaseServiceInputTemplatesService;

    @Test
    public void testGetTemplatesAndTypeByTemplateId(){
        TestcaseServiceInputTemplates testcaseServiceInputTemplates = iTestcaseServiceInputTemplatesService.getTemplatesAndTypeByTemplateId(5);
        System.out.println(testcaseServiceInputTemplates.toString());
        System.out.println(testcaseServiceInputTemplates.getTestDatasetTypesInfo());
    }

}
