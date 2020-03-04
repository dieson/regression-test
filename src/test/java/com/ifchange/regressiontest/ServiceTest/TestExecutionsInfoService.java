package com.ifchange.regressiontest.ServiceTest;

import com.ifchange.regressiontest.model.TestExecutionsInfo;
import com.ifchange.regressiontest.service.ITestExecutionsInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: TestExecutionsInfoService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 17:57 2019/3/29
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestExecutionsInfoService {
    @Autowired
    ITestExecutionsInfoService iTestExecutionsInfoService;

    @Test
    public void testGetExecutionAndSetByExecutionId() {
        TestExecutionsInfo testExecutionsInfo = iTestExecutionsInfoService.getExecutionAndSetByExecutionId(5);
        System.out.println(testExecutionsInfo);

    }
}
