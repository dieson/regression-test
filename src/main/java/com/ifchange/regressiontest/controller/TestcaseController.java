package com.ifchange.regressiontest.controller;

import com.alibaba.fastjson.JSONObject;
import com.ifchange.regressiontest.constant.ResponseCode;
import com.ifchange.regressiontest.dto.ServerResponse;
import com.ifchange.regressiontest.model.*;
import com.ifchange.regressiontest.service.*;
import com.ifchange.regressiontest.util.JsonUtil;
import com.ifchange.regressiontest.util.StringUtil;
import com.ifchange.regressiontest.util.ValidataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TestcasesController
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 11:04 2019/4/9
 */
@RestController
@RequestMapping("/test_case")
public class TestcaseController {
    @Autowired
    private ITestCasesService iTestCasesService;
    @Autowired
    private ITestDataSetsInfoService iTestDataSetsInfoService;
    @Autowired
    private ITestDataSetUploadFormatTemplatesService iTestDataSetUploadFormatTemplatesService;
    @Autowired
    private ITestcaseServiceInputTemplatesService iTestcaseServiceInputTemplatesService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ServerResponse createTestcase(@Validated @RequestBody Testcases testcases, BindingResult result) {
        logger.info("创建Testcase");
        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }
            //获取数据的字段名称及类型
            int setId = testcases.getDatasetId();
            TestDatasetsInfo testDatasetsInfo = iTestDataSetsInfoService.getTestDataSetById(setId);
            int typeId = testDatasetsInfo.getDatasetTypeId();
            TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates = iTestDataSetUploadFormatTemplatesService.getTestDataSetUploadFormatTemplatesByTypeId(typeId);
            String[] requestColumnName = testDatasetUploadFormatTemplates.getRequestColumName().split(",");
            String[] requestColumnType = testDatasetUploadFormatTemplates.getRequestColumDataType().split(",");
            String[] responseColumnName = testDatasetUploadFormatTemplates.getResponseColumName().split(",");
            String[] responseColumnType = testDatasetUploadFormatTemplates.getResponseColumDataType().split(",");
            //将字段类型名称转为可转换类型
            for (int i = 0; i < requestColumnType.length; i++) {
                String typeName = StringUtil.typeToClassName(requestColumnType[i]);
                if (typeName == null) {
                    return ServerResponse.createByErrorMessage("请求字段类型不存在");
                }
                requestColumnType[i] = typeName;
            }
            for (int i = 0; i < responseColumnType.length; i++) {
                String typeName = StringUtil.typeToClassName(responseColumnType[i]);
                if (typeName == null) {
                    return ServerResponse.createByErrorMessage("响应字段类型不存在");
                }
                responseColumnType[i] = typeName;
            }
            //获取数据
            String[] requestColumnData = testcases.getData().split(",");
            String[] responseColumnData = testcases.getExpectData().split(",");
            //获取模板
            TestcaseServiceInputTemplates testcaseServiceInputTemplates = iTestcaseServiceInputTemplatesService.getInputTemplateByDataSetTypeId(typeId);
            String requestTemplate = testcaseServiceInputTemplates.getRequestTemplate();
            String responseTemplate = testcaseServiceInputTemplates.getResponseTemplate();

            for (int i = 0; i < requestColumnName.length; i++) {
                Object value = Class.forName(requestColumnType[i]).cast(requestColumnData[i]);
                if (requestColumnType[i].contains("String")) {
                    requestTemplate = requestTemplate.replace("#{" + requestColumnName[i] + "}", "\"" + value.toString() + "\"");
                } else {
                    requestTemplate = requestTemplate.replace("#{" + requestColumnName[i] + "}", value.toString());
                }
            }
            for (int i = 0; i < responseColumnName.length; i++) {
                Object value = Class.forName(responseColumnType[i]).cast(responseColumnData[i]);
                if (responseColumnType[i].contains("String")) {
                    responseTemplate = responseTemplate.replace("#{" + responseColumnName[i] + "}", "\"" + value.toString() + "\"");
                } else {
                    responseTemplate = responseTemplate.replace("#{" + responseColumnName[i] + "}", value.toString());
                }
            }

            testcases.setData(requestTemplate);
            testcases.setExpectData(responseTemplate);
            int i = iTestCasesService.createTestCases(testcases);
            List<Testcases> tc = iTestCasesService.getTestCases(testcases);
            if (i == 1 && tc != null) {
                //修改测试集中的测试数据总数
                int testcaseTotal = iTestCasesService.getBySetId(setId).size();
                TestDatasetsInfo setInfo = new TestDatasetsInfo();
                setInfo.setId(setId);
                setInfo.setTestCaseCount(testcaseTotal);
                iTestDataSetsInfoService.updateTestDataSet(setInfo);

                return ServerResponse.createBySuccess(tc);
            }
            return ServerResponse.createByErrorMessage("创建Testcase失败");

        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("创建Testcase失败： ", e);
            return ServerResponse.createByErrorMessage("请输入完整字段，并以逗号分隔");
        } catch (Exception e) {
            logger.error("创建Testcase失败： ", e);
            return ServerResponse.createByErrorMessage("创建Testcase失败");
        }
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.PUT)
    public ServerResponse modifyTestcase(@PathVariable Integer id, @RequestBody Testcases testcases) {
        logger.info("修改Testcase");
        try {

            if (iTestCasesService.getTestCasesById(id) == null) {
                return ServerResponse.createByErrorMessage("ID不存在");
            }

            testcases.setId(id);
            int i = iTestCasesService.updateTestCases(testcases);
            Testcases tc = iTestCasesService.getTestCasesById(id);

            if (i == 1 && tc != null) {
                return ServerResponse.createBySuccess(tc);
            }
            return ServerResponse.createByErrorMessage("修改Testcase失败");

        } catch (Exception e) {
            logger.error("修改Testcase失败： ", e);
            return ServerResponse.createByErrorMessage("修改Testcase失败");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ServerResponse listTestcase() {
        logger.info("获取Testcase列表");
        try {
            List<Testcases> testcasesList = iTestCasesService.getAll();
            if (testcasesList.size() == 0) {
                return ServerResponse.createByErrorMessage("获取Testcase列表失败");
            }

            return ServerResponse.createBySuccess(testcasesList);
        } catch (Exception e) {
            logger.error("获取Testcase列表失败： ", e);
            return ServerResponse.createByErrorMessage("获取Testcase列表失败");
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ServerResponse getTestCase(@Validated @RequestBody Page page, BindingResult result) {
        logger.info("获取Test Case");
        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }

            Testcases testcases = JsonUtil.mapToObj(page.getData(), Testcases.class);

            List<Testcases> testcasesList = iTestCasesService.getTestCaseAndSetSelective(testcases);
            int total = testcasesList.size();
            if (total == 0) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.FOUND_RESULT.getCode(), ResponseCode.FOUND_RESULT.getDesc());
            }

            Page p = Page.createPage(page.getCurrentPage(), page.getPageSize(), total, testcasesList);
            return ServerResponse.createBySuccess(p);
        } catch (Exception e) {
            logger.error("获取Test Case失败： ", e);
            return ServerResponse.createByErrorMessage("获取Test Case失败");
        }
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ServerResponse detailTestcase(@PathVariable Integer id) {
        logger.info("查看Testcase详情");
        try {
            if (iTestCasesService.getTestCasesById(id) == null) {
                return ServerResponse.createByErrorMessage("ID不存在");
            }

            Testcases testcases = iTestCasesService.getTestCasesById(id);
            if (testcases == null) {
                return ServerResponse.createByErrorMessage("获取Testcase详情失败");
            }

            return ServerResponse.createBySuccess(testcases);
        } catch (Exception e) {
            logger.error("获取Testcase详情失败： ", e);
            return ServerResponse.createByErrorMessage("获取Testcase详情失败");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ServerResponse deleteTestcase(@PathVariable Integer id) {
        logger.info("删除Testcase");
        try {
            Testcases testcases = iTestCasesService.getTestCasesById(id);
            if (testcases == null) {
                return ServerResponse.createByErrorMessage("ID不存在");
            }

            if (iTestCasesService.deleteTestCases(id) != 1) {
                return ServerResponse.createByErrorMessage("删除Testcase失败");
            }
            //修改测试集中的测试数据总数
            int setId = testcases.getDatasetId();
            int testcaseTotal = iTestCasesService.getBySetId(setId).size();
            TestDatasetsInfo setInfo = new TestDatasetsInfo();
            setInfo.setId(setId);
            setInfo.setTestCaseCount(testcaseTotal);
            iTestDataSetsInfoService.updateTestDataSet(setInfo);

            return ServerResponse.createBySuccessMessage("Testcase删除成功");
        } catch (Exception e) {
            logger.error("删除Testcase失败： ", e);
            return ServerResponse.createByErrorMessage("删除Testcase失败");
        }
    }

    @RequestMapping(value = "/batch_delete", method = RequestMethod.POST)
    public ServerResponse batchDeleteTestcase(@RequestBody Map<String, Object> requestData) {
        logger.info("批量删除Test Case");
        try {
            List ids = (List) requestData.get("ids");
            List<Testcases> testcasesList = iTestCasesService.getByPrimaryKeyList(ids);
            if (testcasesList.size() != ids.size()) {
                return ServerResponse.createByErrorMessage("Id不正确");
            }

            if (iTestCasesService.deleteByPrimaryKeyList(ids) != ids.size()) {
                return ServerResponse.createByErrorMessage("批量除Test Case失败");
            }
            //修改测试集中的测试数据总数
            Map<Integer, Integer> testcaseCount = new HashMap<>();
            for (Testcases testcase : testcasesList) {
                Integer setId = testcase.getDatasetId();
                if (testcaseCount.containsKey(setId)) {
                    testcaseCount.put(setId, 1);
                } else {
                    testcaseCount.put(setId, testcaseCount.get(setId) + 1);
                }
            }
            for (Integer setId : testcaseCount.keySet()) {
                TestDatasetsInfo setInfo = new TestDatasetsInfo();
                setInfo.setId(setId);
                setInfo.setTestCaseCount(testcaseCount.get(setId));
                iTestDataSetsInfoService.updateTestDataSet(setInfo);
            }

            return ServerResponse.createBySuccessMessage("Test Case批量删除成功");
        } catch (Exception e) {
            logger.error("批量删除Test Case失败： ", e);
            return ServerResponse.createByErrorMessage("批量删除Test Case失败");
        }
    }

}
