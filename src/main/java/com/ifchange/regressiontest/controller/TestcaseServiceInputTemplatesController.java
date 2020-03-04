package com.ifchange.regressiontest.controller;

import com.ifchange.regressiontest.constant.ResponseCode;
import com.ifchange.regressiontest.dto.ServerResponse;
import com.ifchange.regressiontest.model.Page;
import com.ifchange.regressiontest.model.TestcaseServiceInputTemplates;
import com.ifchange.regressiontest.service.ITestcaseServiceInputTemplatesService;
import com.ifchange.regressiontest.util.JsonUtil;
import com.ifchange.regressiontest.util.ValidataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TestcaseServiceInputTemplatesController
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 17:12 2019/4/4
 */
@RestController
@RequestMapping("/input_template")
public class TestcaseServiceInputTemplatesController {
    @Autowired
    ITestcaseServiceInputTemplatesService iTestcaseServiceInputTemplatesService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ServerResponse createInputTemplate(@Validated @RequestBody TestcaseServiceInputTemplates testcaseServiceInputTemplates, BindingResult result) {
        logger.info("创建Input Template");
        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }

            if (iTestcaseServiceInputTemplatesService.getInputTemplateByDataSetTypeId(testcaseServiceInputTemplates.getDatasetTypeId()) != null) {
                return ServerResponse.createByErrorMessage("该测试类型模板已存在");
            }

            int i = iTestcaseServiceInputTemplatesService.createTestcaseServiceInputTemplates(testcaseServiceInputTemplates);
            TestcaseServiceInputTemplates inputTemplates = iTestcaseServiceInputTemplatesService.getTestcaseServiceInputTemplates(testcaseServiceInputTemplates);
            if (i == 1 && inputTemplates != null) {
                return ServerResponse.createBySuccess(inputTemplates);
            }
            return ServerResponse.createByErrorMessage("创建Input Template失败");

        } catch (Exception e) {
            logger.error("创建Input Template失败： ", e);
            return ServerResponse.createByErrorMessage("创建Input Template失败");
        }
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.PUT)
    public ServerResponse modifyInputTemplates(@PathVariable Integer id, @RequestBody TestcaseServiceInputTemplates testcaseServiceInputTemplates) {
        logger.info("修改Input Template");
        try {
            TestcaseServiceInputTemplates temp = iTestcaseServiceInputTemplatesService.getInputTemplateByDataSetTypeId(testcaseServiceInputTemplates.getDatasetTypeId());
            if (!temp.getId().equals(id)){
                return ServerResponse.createByErrorMessage("该测试类型模板已存在");
            }

            testcaseServiceInputTemplates.setId(id);
            int i = iTestcaseServiceInputTemplatesService.updateTestcaseServiceInputTemplates(testcaseServiceInputTemplates);
            TestcaseServiceInputTemplates inputTemplates = iTestcaseServiceInputTemplatesService.getTestcaseServiceInputTemplatesById(id);

            if (i == 1 && inputTemplates != null) {
                return ServerResponse.createBySuccess(inputTemplates);
            }
            return ServerResponse.createByErrorMessage("修改Input Template失败");

        } catch (Exception e) {
            logger.error("修改Input Template失败： ", e);
            return ServerResponse.createByErrorMessage("修改Input Template失败");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ServerResponse listInputTemplate() {
        logger.info("获取Input Template列表");
        try {
            List<TestcaseServiceInputTemplates> testcaseServiceInputTemplatesList = iTestcaseServiceInputTemplatesService.getTemplatesAll();
            if (testcaseServiceInputTemplatesList.size() == 0) {
                return ServerResponse.createByErrorMessage("获取Input Template列表失败");
            }

            return ServerResponse.createBySuccess(testcaseServiceInputTemplatesList);
        } catch (Exception e) {
            logger.error("获取Input Template列表失败： ", e);
            return ServerResponse.createByErrorMessage("获取Input Template列表失败");
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ServerResponse getInputTemplate(@Validated @RequestBody Page page, BindingResult result) {
        logger.info("获取Input Template");
        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }
            TestcaseServiceInputTemplates testcaseServiceInputTemplates = JsonUtil.jsonToObject(JsonUtil.objToJson(page.getData()), TestcaseServiceInputTemplates.class);

            List<TestcaseServiceInputTemplates> testcaseServiceInputTemplatesList = iTestcaseServiceInputTemplatesService.getTemplatesAndTypeSelective(testcaseServiceInputTemplates);
            int total = testcaseServiceInputTemplatesList.size();
            if (total == 0) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.FOUND_RESULT.getCode(), ResponseCode.FOUND_RESULT.getDesc());
            }

            Page p = Page.createPage(page.getCurrentPage(), page.getPageSize(), total, testcaseServiceInputTemplatesList);
            return ServerResponse.createBySuccess(p);
        } catch (Exception e) {
            logger.error("获取Input Template失败： ", e);
            return ServerResponse.createByErrorMessage("获取Input Template失败");
        }

    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ServerResponse detailInputTemplate(@PathVariable Integer id) {
        logger.info("查看Input Template详情");
        try {
            TestcaseServiceInputTemplates testcaseServiceInputTemplates = iTestcaseServiceInputTemplatesService.getTestcaseServiceInputTemplatesById(id);
            if (testcaseServiceInputTemplates == null) {
                return ServerResponse.createByErrorMessage("获取Input Template详情失败");
            }

            return ServerResponse.createBySuccess(testcaseServiceInputTemplates);
        } catch (Exception e) {
            logger.error("获取Input Template详情失败： ", e);
            return ServerResponse.createByErrorMessage("获取Input Template详情失败");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ServerResponse deleteInputTemplate(@PathVariable Integer id) {
        logger.info("删除Input Template");
        try {

            if (iTestcaseServiceInputTemplatesService.getTestcaseServiceInputTemplatesById(id) == null) {
                return ServerResponse.createByErrorMessage("id不存在");
            }

            if (iTestcaseServiceInputTemplatesService.deleteTestcaseServiceInputTemplates(id) != 1) {
                return ServerResponse.createByErrorMessage("删除Input Template失败");
            }

            return ServerResponse.createBySuccessMessage("Input Template删除成功");
        } catch (Exception e) {
            logger.error("删除Input Template失败： ", e);
            return ServerResponse.createByErrorMessage("删除Input Template失败");
        }
    }

    @RequestMapping(value = "/batch_delete", method = RequestMethod.POST)
    public ServerResponse batchDeleteInputTemplate(@RequestBody Map<String, Object> requestData) {
        logger.info("批量删除Input Template");
        try {
            List ids = (List) requestData.get("ids");

            if(iTestcaseServiceInputTemplatesService.getByPrimaryKeyList(ids).size() != ids.size()){
                return ServerResponse.createByErrorMessage("Id不正确");
            }

            if(iTestcaseServiceInputTemplatesService.deleteByPrimaryKeyList(ids) != ids.size()){
                return ServerResponse.createByErrorMessage("批量除Input Template失败");
            }

            return ServerResponse.createBySuccessMessage("Input Template批量删除成功");
        } catch (Exception e) {
            logger.error("批量删除Input Template失败： ", e);
            return ServerResponse.createByErrorMessage("批量删除Input Template失败");
        }
    }

}
