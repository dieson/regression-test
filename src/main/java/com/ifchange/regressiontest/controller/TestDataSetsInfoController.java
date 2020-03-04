package com.ifchange.regressiontest.controller;

import com.ifchange.regressiontest.constant.Const;
import com.ifchange.regressiontest.constant.ResponseCode;
import com.ifchange.regressiontest.dto.ServerResponse;
import com.ifchange.regressiontest.model.*;
import com.ifchange.regressiontest.service.ITestDataSetsInfoService;
import com.ifchange.regressiontest.service.ITokenService;
import com.ifchange.regressiontest.service.IUserService;
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
 * @ClassName: TestDataSetsInfoController
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 15:45 2019/4/8
 */
@RestController
@RequestMapping("/test_set")
public class TestDataSetsInfoController {
    @Autowired
    ITestDataSetsInfoService iTestDataSetsInfoService;
    @Autowired
    ITokenService iTokenService;
    @Autowired
    IUserService iUserService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ServerResponse createTestSet(HttpServletRequest request, @Validated @RequestBody TestDatasetsInfo testDatasetsInfo, BindingResult result) {
        logger.info("创建Test Set");
        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }

            if (iTestDataSetsInfoService.getByName(testDatasetsInfo.getName()) != null) {
                return ServerResponse.createByErrorMessage("Name已存在");
            }

            String token = request.getHeader(Const.TOKEN);
            Token tokenEntity = iTokenService.getToken(token);
            User user = iUserService.getUserById(tokenEntity.getUserId());
            testDatasetsInfo.setCreatedBy(user.getUsername());

            int i = iTestDataSetsInfoService.createTestDataSet(testDatasetsInfo);
            List<TestDatasetsInfo> testSet = iTestDataSetsInfoService.getTestDataSet(testDatasetsInfo);
            if (i == 1 && testSet != null) {
                return ServerResponse.createBySuccess(testSet);
            }
            return ServerResponse.createByErrorMessage("创建Test Set失败");

        } catch (Exception e) {
            logger.error("创建Test Set失败： ", e);
            return ServerResponse.createByErrorMessage("创建Test Set失败");
        }
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.PUT)
    public ServerResponse modifyTestSet(@PathVariable Integer id, @RequestBody TestDatasetsInfo testDatasetsInfo) {
        logger.info("修改Test Set");
        try {

            if (iTestDataSetsInfoService.getTestDataSetById(id) == null) {
                return ServerResponse.createByErrorMessage("ID不存在");
            }

            testDatasetsInfo.setId(id);
            int i = iTestDataSetsInfoService.updateTestDataSet(testDatasetsInfo);
            TestDatasetsInfo testSet = iTestDataSetsInfoService.getTestDataSetById(id);

            if (i == 1 && testSet != null) {
                return ServerResponse.createBySuccess(testSet);
            }
            return ServerResponse.createByErrorMessage("修改Test Set失败");

        } catch (Exception e) {
            logger.error("修改Test Set失败： ", e);
            return ServerResponse.createByErrorMessage("修改Test Set失败");
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ServerResponse getSet(@Validated @RequestBody Page page, BindingResult result) {
        logger.info("获取Set列表");
        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }
            TestDatasetsInfo testDatasetsInfo = JsonUtil.jsonToObject(JsonUtil.objToJson(page.getData()), TestDatasetsInfo.class);

            List<TestDatasetsInfo> testDatasetsInfoList = iTestDataSetsInfoService.getSetAndTypeSelective(testDatasetsInfo);
            int total = testDatasetsInfoList.size();
            if (total == 0) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.FOUND_RESULT.getCode(), ResponseCode.FOUND_RESULT.getDesc());
            }

            Page p = Page.createPage(page.getCurrentPage(), page.getPageSize(), total, testDatasetsInfoList);
            return ServerResponse.createBySuccess(p);
        } catch (Exception e) {
            logger.error("获取Set列表失败： ", e);
            return ServerResponse.createByErrorMessage("获取Set列表失败");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ServerResponse setList() {
        logger.info("获取Set列表");
        try {
            List<TestDatasetsInfo> testDatasetsInfoList = iTestDataSetsInfoService.getAll();
            if (testDatasetsInfoList.size() == 0) {
                return ServerResponse.createByErrorMessage("获取Set列表失败");
            }
            return ServerResponse.createBySuccess(testDatasetsInfoList);
        } catch (Exception e) {
            logger.error("获取Set列表失败： ", e);
            return ServerResponse.createByErrorMessage("获取Set列表失败");
        }
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ServerResponse detailTestSet(@PathVariable Integer id) {
        logger.info("查看Test Set详情");
        try {
            TestDatasetsInfo testDatasetsInfo = iTestDataSetsInfoService.getTestDataSetById(id);
            if (testDatasetsInfo == null) {
                return ServerResponse.createByErrorMessage("获取Test Set详情失败");
            }

            return ServerResponse.createBySuccess(testDatasetsInfo);
        } catch (Exception e) {
            logger.error("获取Test Set详情失败： ", e);
            return ServerResponse.createByErrorMessage("获取Test Set详情失败");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ServerResponse deleteTestSet(@PathVariable Integer id) {
        logger.info("删除Test Set");
        try {

            if (iTestDataSetsInfoService.getTestDataSetById(id) == null) {
                return ServerResponse.createByErrorMessage("id不存在");
            }

            if (iTestDataSetsInfoService.deleteTestDataSet(id) != 1) {
                return ServerResponse.createByErrorMessage("删除Test Set失败");
            }

            return ServerResponse.createBySuccessMessage("Test Set删除成功");
        } catch (Exception e) {
            logger.error("删除Test Set失败： ", e);
            return ServerResponse.createByErrorMessage("删除Test Set失败");
        }
    }

    @RequestMapping(value = "/batch_delete", method = RequestMethod.POST)
    public ServerResponse deleteSetList(@RequestBody Map<String, Object> requestData) {
        logger.info("批量删除Set");
        try {
            List ids = (List) requestData.get("ids");
            if (iTestDataSetsInfoService.getSetByPrimaryKeyList(ids).size() != ids.size()) {
                return ServerResponse.createByErrorMessage("Set id不正确");
            }

            if (iTestDataSetsInfoService.deleteSetByPrimaryKeyList(ids) != ids.size()) {
                return ServerResponse.createByErrorMessage("删除Set失败");
            }

            return ServerResponse.createBySuccessMessage("Set删除成功");
        } catch (Exception e) {
            logger.error("批量删除Set失败： ", e);
            return ServerResponse.createByErrorMessage("批量删除Set失败");
        }
    }

}
