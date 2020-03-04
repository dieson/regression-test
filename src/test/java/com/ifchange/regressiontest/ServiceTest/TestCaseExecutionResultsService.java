package com.ifchange.regressiontest.ServiceTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ifchange.regressiontest.model.TestcaseExecutionResults;
import com.ifchange.regressiontest.service.ITestCaseExecutionResultsService;
import com.ifchange.regressiontest.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestCaseExecutionResultsService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 10:38 2019/3/25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCaseExecutionResultsService {
    @Autowired
    ITestCaseExecutionResultsService iTestCaseExecutionResultsService;

    @Test
    public void testDemo() {
        JSONObject a = new JSONObject();
        a.put("name","奥莱加盟店运营岗");
        a.put("description","工作内容:(1)负责加盟店铺运营管理工作，完成年度销售目标。(2)负责传达公司营销政策及相关规定。(3)负责加盟店铺形象升级、重装、扩店等整合推进工作。(4)负责加盟店铺销售扶持工作，节假日驻店支持工作。(5)负责加盟店铺大型营销活动开展工作。(6)负责加盟店铺运营过程中的优劣势分析，制定改进落地方案。(7)负责监督加盟店铺促销规范及管控。(8)负责监督加盟店铺陈列形象、人员形象等工作。(9)负责监督加盟店铺VIP会员的发展及维护工作。(10)负责监督加盟店铺相关培训学习落地执行情况。(11)负责定期召集加盟店铺店长学习分享会。(12)负责监督加盟店铺微信群相关管理工作。(13)完成上级领导交办的其他任务。职位要求:大专以上文化学历；(2)适应不定期出差及经常加班；(3)一年以上终端店铺管理经验；(4)具备良好沟通协调能力；(5)具备一定的销售能力；(6)对带教店员有较好的经验；(7)有加盟商管理经验者优先。");
        JsonUtil.objToJson(a);

        String b = "{'name':'奥莱加盟店运营岗','description':'工作内容:(1)负责加盟店铺运营管理工作，完成年度销售目标。(2)负责传达公司营销政策及相关规定。(3)负责加盟店铺形象升级、重装、扩店等整合推进工作。(4)负责加盟店铺销售扶持工作，节假日驻店支持工作。(5)负责加盟店铺大型营销活动开展工作。(6)负责加盟店铺运营过程中的优劣势分析，制定改进落地方案。(7)负责监督加盟店铺促销规范及管控。(8)负责监督加盟店铺陈列形象、人员形象等工作。(9)负责监督加盟店铺VIP会员的发展及维护工作。(10)负责监督加盟店铺相关培训学习落地执行情况。(11)负责定期召集加盟店铺店长学习分享会。(12)负责监督加盟店铺微信群相关管理工作。(13)完成上级领导交办的其他任务。职位要求:大专以上文化学历；(2)适应不定期出差及经常加班；(3)一年以上终端店铺管理经验；(4)具备良好沟通协调能力；(5)具备一定的销售能力；(6)对带教店员有较好的经验；(7)有加盟商管理经验者优先。'}";
        System.out.println(JsonUtil.strToJson(b));
    }

    @Test
    public void testAdd() {
        TestcaseExecutionResults testcaseExecutionResults = new TestcaseExecutionResults();
        testcaseExecutionResults.setTestExecutionId(5);
        testcaseExecutionResults.setTestcaseId(6);
        testcaseExecutionResults.setExecutionStatus("successful");
        testcaseExecutionResults.setResultData("{'':''}");
        testcaseExecutionResults.setPassed(true);
        testcaseExecutionResults.setCorrectness("测试");
        testcaseExecutionResults.setCorrectnessScore(1.0);

        int i = iTestCaseExecutionResultsService.addTestCaseExecutionResults(testcaseExecutionResults);
        System.out.println(i);
    }

    @Test
    public void testGetExecutionResultAndTestcaseById() {
        TestcaseExecutionResults testcaseExecutionResults = iTestCaseExecutionResultsService.getExecutionResultAndTestcaseById(5);
        System.out.println(testcaseExecutionResults.toString());
        System.out.println(testcaseExecutionResults.getTestcases());
    }

    @Test
    public void testGetExecutionResultAndExecutionById() {
        TestcaseExecutionResults testcaseExecutionResults = iTestCaseExecutionResultsService.getExecutionResultAndExecutionById(5);
        System.out.println(testcaseExecutionResults.toString());
        System.out.println(testcaseExecutionResults.getTestExecutionsInfo());
    }
}
