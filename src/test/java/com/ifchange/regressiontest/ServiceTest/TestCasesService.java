package com.ifchange.regressiontest.ServiceTest;

import com.ifchange.regressiontest.model.Testcases;
import com.ifchange.regressiontest.service.ITestCasesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName: TestCasesService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 11:46 2019/3/29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCasesService {
    @Autowired
    ITestCasesService iTestCasesService;

    @Test
    public void testCreateTestCases() {
        Testcases testcases = new Testcases();
        testcases.setDatasetId(3);
        testcases.setData("{'json':'test'}");
        iTestCasesService.createTestCases(testcases);
    }

    @Test
    public void tesUpdateTestCases() {
        Testcases testcases = new Testcases();
        testcases.setId(6);
        testcases.setDatasetId(3);
        testcases.setData("{'json':'update'}");
        iTestCasesService.updateTestCases(testcases);
    }

    @Test
    public void testGetTestCases() {
        Testcases testcases = new Testcases();
        testcases.setId(6);
        testcases.setDatasetId(3);
        testcases.setData("{'json':'update'}");
        List<Testcases> tc = iTestCasesService.getTestCases(testcases);
        System.out.println(tc);
    }

    @Test
    public void testDeleteTestCases() {
        int i = iTestCasesService.deleteTestCases(5);
        System.out.println(i);
    }

    @Test
    public void testGetTestCasesById() {
        Testcases tc = iTestCasesService.getTestCasesById(6);
        System.out.println(tc);
    }

    @Test
    public void testGetTestcaseAndSetInfoByCaseId() {
        Testcases testcases = iTestCasesService.getTestcaseAndSetInfoByCaseId(6);
        System.out.println(testcases);
        System.out.println(testcases.getTestDatasetsInfo());
    }
}
