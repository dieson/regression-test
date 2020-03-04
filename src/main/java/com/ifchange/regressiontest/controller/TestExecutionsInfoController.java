package com.ifchange.regressiontest.controller;

import com.ifchange.regressiontest.constant.Const;
import com.ifchange.regressiontest.constant.ResponseCode;
import com.ifchange.regressiontest.dto.ServerResponse;
import com.ifchange.regressiontest.model.*;
import com.ifchange.regressiontest.service.*;
import com.ifchange.regressiontest.util.JsonUtil;
import com.ifchange.regressiontest.util.ValidataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TestExecutionsInfoController
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 13:56 2019/4/9
 */
@RestController
@RequestMapping("/executions")
public class TestExecutionsInfoController {
    @Autowired
    private ITestExecutionsInfoService iTestExecutionsInfoService;
    @Autowired
    private ITestDataSetsInfoService iTestDataSetsInfoService;
    @Autowired
    private ITokenService iTokenService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ITaskService iTaskService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ServerResponse createExecution(HttpServletRequest request, @Validated @RequestBody TestExecutionsInfo testExecutionsInfo, BindingResult result) {
        logger.info("创建Execution");
        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }

            if (iTestExecutionsInfoService.getExecutionByName(testExecutionsInfo.getName()) != null) {
                return ServerResponse.createByErrorMessage("Name已存在");
            }

            //获取testcase总数
            int setId = testExecutionsInfo.getTestDatasetId();
            TestDatasetsInfo setAndTestcaseList = iTestDataSetsInfoService.getSetAndTestcaseBySetId(setId);
            List<Testcases> testCasesList = setAndTestcaseList.getTestcasesList();
            testExecutionsInfo.setTestcaseCount(testCasesList.size());
            //获取service_address
            TestDatasetsInfo setAndTypeList = iTestDataSetsInfoService.getSetAndTypeBySetId(setId);
            TestDatasetTypesInfo testDatasetTypesInfo = setAndTypeList.getTestDatasetTypesInfo();
            testExecutionsInfo.setServiceAddress(testDatasetTypesInfo.getDefaultServiceAddress());
            //获取create_by
            String token = request.getHeader(Const.TOKEN);
            Token tokenEntity = iTokenService.getToken(token);
            User user = iUserService.getUserById(tokenEntity.getUserId());
            testExecutionsInfo.setCreatedBy(user.getUsername());

            int i = iTestExecutionsInfoService.createTestExecutionsInfo(testExecutionsInfo);
            TestExecutionsInfo tei = iTestExecutionsInfoService.getTestExecutionsInfo(testExecutionsInfo);

            if (i == 1 && tei != null) {
                iTaskService.executionTestcase(tei.getId());
                return ServerResponse.createBySuccess(tei);
            }
            return ServerResponse.createByErrorMessage("创建Execution失败");

        } catch (Exception e) {
            logger.error("创建Execution失败： ", e);
            return ServerResponse.createByErrorMessage("创建Execution失败");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ServerResponse listExecution() {
        logger.info("获取Execution列表");
        try {
            List<TestExecutionsInfo> testExecutionsInfoList = iTestExecutionsInfoService.getAll();
            if (testExecutionsInfoList.size() == 0) {
                return ServerResponse.createByErrorMessage("获取Execution列表失败");
            }

            return ServerResponse.createBySuccess(testExecutionsInfoList);
        } catch (Exception e) {
            logger.error("获取Execution列表失败： ", e);
            return ServerResponse.createByErrorMessage("获取Execution列表失败");
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ServerResponse getExecution(@Validated @RequestBody Page page, BindingResult result) {
        logger.info("查询Execution列表");
        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }

            TestExecutionsInfo testExecutionsInfo = JsonUtil.jsonToObject(JsonUtil.objToJson(page.getData()), TestExecutionsInfo.class);
            List<TestExecutionsInfo> testExecutionsInfoList = iTestExecutionsInfoService.getExecutionAndSetSelective(testExecutionsInfo);
            int total = testExecutionsInfoList.size();
            Page p = Page.createPage(page.getCurrentPage(), page.getPageSize(), total, testExecutionsInfoList);

            if (testExecutionsInfoList.size() == 0) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.FOUND_RESULT.getCode(), ResponseCode.FOUND_RESULT.getDesc());
            }

            return ServerResponse.createBySuccess(p);
        } catch (Exception e) {
            logger.error("查询Execution列表失败： ", e);
            return ServerResponse.createByErrorMessage("查询Execution列表失败");
        }
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ServerResponse detailExecution(@PathVariable Integer id) {
        logger.info("查看Execution详情");
        try {
            TestExecutionsInfo testExecutionsInfo = iTestExecutionsInfoService.getTestExecutionsInfoById(id);
            if (testExecutionsInfo == null) {
                return ServerResponse.createByErrorMessage("获取Execution详情失败");
            }

            return ServerResponse.createBySuccess(testExecutionsInfo);
        } catch (Exception e) {
            logger.error("获取Execution详情失败： ", e);
            return ServerResponse.createByErrorMessage("获取Execution详情失败");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ServerResponse deleteExecution(@PathVariable Integer id) {
        logger.info("删除Input Template");
        try {

            if (iTestExecutionsInfoService.getTestExecutionsInfoById(id) == null) {
                return ServerResponse.createByErrorMessage("id不存在");
            }

            if (iTestExecutionsInfoService.deleteTestExecutionsInfo(id) != 1) {
                return ServerResponse.createByErrorMessage("删除Execution失败");
            }

            return ServerResponse.createBySuccessMessage("Execution删除成功");
        } catch (Exception e) {
            logger.error("删除Execution失败： ", e);
            return ServerResponse.createByErrorMessage("删除Execution失败");
        }
    }

    @RequestMapping(value = "/batch_delete", method = RequestMethod.POST)
    public ServerResponse batchDeleteExecution(@RequestBody Map<String, Object> requestData) {
        logger.info("批量删除Execution");
        try {
            List ids = (List) requestData.get("ids");
            if (iTestExecutionsInfoService.getByPrimaryKeyList(ids).size() != ids.size()) {
                return ServerResponse.createByErrorMessage("Execution id不正确");
            }

            if (iTestExecutionsInfoService.deleteByPrimaryKeyList(ids) != ids.size()) {
                return ServerResponse.createByErrorMessage("删除Execution失败");
            }

            return ServerResponse.createBySuccessMessage("批量删除Execution成功");
        } catch (Exception e) {
            logger.error("批量删除Execution失败： ", e);
            return ServerResponse.createByErrorMessage("批量删除Execution失败");
        }
    }


}
