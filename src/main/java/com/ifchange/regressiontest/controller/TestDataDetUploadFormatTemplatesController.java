package com.ifchange.regressiontest.controller;

import com.ifchange.regressiontest.constant.ResponseCode;
import com.ifchange.regressiontest.dto.ServerResponse;
import com.ifchange.regressiontest.model.Page;
import com.ifchange.regressiontest.model.TestDatasetUploadFormatTemplates;
import com.ifchange.regressiontest.service.ITestDataSetUploadFormatTemplatesService;
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
 * @ClassName: TestDataDetUploadFormatTemplatesController
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 13:50 2019/4/8
 */
@RestController
@RequestMapping("/upload_template")
public class TestDataDetUploadFormatTemplatesController {
    @Autowired
    ITestDataSetUploadFormatTemplatesService iTestDataSetUploadFormatTemplatesService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ServerResponse createFormatTemplate(@Validated @RequestBody TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates, BindingResult result) {
        logger.info("创建Upload Template");
        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }

            if (iTestDataSetUploadFormatTemplatesService.getTestDataSetUploadFormatTemplatesByTypeId(testDatasetUploadFormatTemplates.getDatasetTypeId()) != null) {
                return ServerResponse.createByErrorMessage("该测试类型字段格式已创建");
            }

            int i = iTestDataSetUploadFormatTemplatesService.createTestDataSetUploadFormatTemplates(testDatasetUploadFormatTemplates);
            TestDatasetUploadFormatTemplates formatTemplates = iTestDataSetUploadFormatTemplatesService.getTestDataSetUploadFormatTemplates(testDatasetUploadFormatTemplates);
            if (i == 1 && formatTemplates != null) {
                return ServerResponse.createBySuccess(formatTemplates);
            }
            return ServerResponse.createByErrorMessage("创建Upload Template失败");

        } catch (Exception e) {
            logger.error("创建Upload Template失败： ", e);
            return ServerResponse.createByErrorMessage("创建Upload Template失败");
        }
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.PUT)
    public ServerResponse modifyFormatTemplates(@PathVariable Integer id, @RequestBody TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates) {
        logger.info("修改Upload Template");
        try {
            TestDatasetUploadFormatTemplates upload = iTestDataSetUploadFormatTemplatesService.getTestDataSetUploadFormatTemplatesByTypeId(testDatasetUploadFormatTemplates.getDatasetTypeId());
            if (!upload.getId().equals(id)){
                return ServerResponse.createByErrorMessage("该测试类型模板已存在");
            }

            testDatasetUploadFormatTemplates.setId(id);
            int i = iTestDataSetUploadFormatTemplatesService.updateTestDataSetUploadFormatTemplates(testDatasetUploadFormatTemplates);
            TestDatasetUploadFormatTemplates formatTemplates = iTestDataSetUploadFormatTemplatesService.getTestDataSetUploadFormatTemplatesById(id);

            if (i == 1 && formatTemplates != null) {
                return ServerResponse.createBySuccess(formatTemplates);
            }
            return ServerResponse.createByErrorMessage("修改Upload Template失败");

        } catch (Exception e) {
            logger.error("修改Upload Template失败： ", e);
            return ServerResponse.createByErrorMessage("修改Upload Template失败");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ServerResponse listFormatTemplate() {
        logger.info("获取Upload Template列表");
        try {
            List<TestDatasetUploadFormatTemplates> testDatasetUploadFormatTemplatesList = iTestDataSetUploadFormatTemplatesService.getAll();
            if (testDatasetUploadFormatTemplatesList.size() == 0) {
                return ServerResponse.createByErrorMessage("获取Upload Template列表失败");
            }

            return ServerResponse.createBySuccess(testDatasetUploadFormatTemplatesList);
        } catch (Exception e) {
            logger.error("获取Upload Template列表失败： ", e);
            return ServerResponse.createByErrorMessage("获取Upload Template列表失败");
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ServerResponse getUpdateTemplate(@Validated @RequestBody Page page, BindingResult result) {
        logger.info("获取Upload Template列表");
        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }
            TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates = JsonUtil.jsonToObject(JsonUtil.objToJson(page.getData()), TestDatasetUploadFormatTemplates.class);

            List<TestDatasetUploadFormatTemplates> testDatasetUploadFormatTemplatesList = iTestDataSetUploadFormatTemplatesService.getUploadTemplatesAndTypeSelective(testDatasetUploadFormatTemplates);
            int total = testDatasetUploadFormatTemplatesList.size();
            if (total == 0) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.FOUND_RESULT.getCode(), ResponseCode.FOUND_RESULT.getDesc());
            }

            Page p = Page.createPage(page.getCurrentPage(), page.getPageSize(), total, testDatasetUploadFormatTemplatesList);
            return ServerResponse.createBySuccess(p);
        } catch (Exception e) {
            logger.error("获取Upload Template列表失败： ", e);
            return ServerResponse.createByErrorMessage("获取Upload Template列表失败");
        }
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ServerResponse detailFormatTemplate(@PathVariable Integer id) {
        logger.info("查看Upload Template详情");
        try {
            TestDatasetUploadFormatTemplates testDatasetUploadFormatTemplates = iTestDataSetUploadFormatTemplatesService.getTestDataSetUploadFormatTemplatesById(id);
            if (testDatasetUploadFormatTemplates == null) {
                return ServerResponse.createByErrorMessage("获取Upload Template详情失败");
            }

            return ServerResponse.createBySuccess(testDatasetUploadFormatTemplates);
        } catch (Exception e) {
            logger.error("获取Upload Template详情失败： ", e);
            return ServerResponse.createByErrorMessage("获取Upload Template详情失败");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ServerResponse deleteFormatTemplate(@PathVariable Integer id) {
        logger.info("删除Upload Template");
        try {

            if (iTestDataSetUploadFormatTemplatesService.getTestDataSetUploadFormatTemplatesById(id) == null) {
                return ServerResponse.createByErrorMessage("id不存在");
            }

            if (iTestDataSetUploadFormatTemplatesService.deleteTestDataSetUploadFormatTemplates(id) != 1) {
                return ServerResponse.createByErrorMessage("删除Upload Template失败");
            }

            return ServerResponse.createBySuccessMessage("Upload Template删除成功");
        } catch (Exception e) {
            logger.error("删除Upload Template失败： ", e);
            return ServerResponse.createByErrorMessage("删除Upload Template失败");
        }
    }

    @RequestMapping(value = "/batch_delete", method = RequestMethod.POST)
    public ServerResponse batchDeleteUploadTemplate(@RequestBody Map<String, Object> requestData){
        logger.info("批量删除Upload Template");
        try {
            List ids = (List) requestData.get("ids");
            if (iTestDataSetUploadFormatTemplatesService.getByPrimaryKeyList(ids).size() != ids.size()) {
                return ServerResponse.createByErrorMessage("Id不正确");
            }

            if (iTestDataSetUploadFormatTemplatesService.deleteByPrimaryKeyList(ids) != ids.size()) {
                return ServerResponse.createByErrorMessage("批量除Upload Template失败");
            }

            return ServerResponse.createBySuccessMessage("Upload Template批量删除成功");
        } catch (Exception e) {
            logger.error("批量删除Upload Template失败： ", e);
            return ServerResponse.createByErrorMessage("批量删除Upload Template失败");
        }
    }

}
