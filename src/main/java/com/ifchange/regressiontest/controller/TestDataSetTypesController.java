package com.ifchange.regressiontest.controller;

import com.ifchange.regressiontest.constant.Const;
import com.ifchange.regressiontest.constant.ResponseCode;
import com.ifchange.regressiontest.dto.ServerResponse;
import com.ifchange.regressiontest.model.Page;
import com.ifchange.regressiontest.model.TestDatasetTypesInfo;
import com.ifchange.regressiontest.model.Token;
import com.ifchange.regressiontest.model.User;
import com.ifchange.regressiontest.service.ITestDataSetTypesService;
import com.ifchange.regressiontest.service.ITokenService;
import com.ifchange.regressiontest.service.IUserService;
import com.ifchange.regressiontest.util.JsonUtil;
import com.ifchange.regressiontest.util.StringUtil;
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
 * @ClassName: TestDataSetTypesController
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 15:51 2019/4/2
 */
@RestController
@RequestMapping("/type")
public class TestDataSetTypesController {
    @Autowired
    ITestDataSetTypesService iTestDataSetTypesService;
    @Autowired
    ITokenService iTokenService;
    @Autowired
    IUserService iUserService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ServerResponse createType(HttpServletRequest request, @Validated @RequestBody TestDatasetTypesInfo testDatasetTypesInfo, BindingResult result) {
        logger.info("创建type");
        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }

            if (iTestDataSetTypesService.getTypeByName(testDatasetTypesInfo.getName()) != null) {
                return ServerResponse.createByErrorMessage("name已存在");
            }

            String token = request.getHeader(Const.TOKEN);
            Token tokenEntity = iTokenService.getToken(token);
            User user = iUserService.getUserById(tokenEntity.getUserId());
            testDatasetTypesInfo.setCreatedBy(user.getUsername());

            int i = iTestDataSetTypesService.createTestDataSetType(testDatasetTypesInfo);
            List<TestDatasetTypesInfo> type = iTestDataSetTypesService.getTestDataSetType(testDatasetTypesInfo);
            if (i == 1 && type != null) {
                return ServerResponse.createBySuccess(type);
            }
            return ServerResponse.createByErrorMessage("创建失败");

        } catch (Exception e) {
            logger.error("创建失败： ", e);
            return ServerResponse.createByErrorMessage("创建失败");
        }
    }

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public ServerResponse typeList(){
        logger.info("获取Type列表");
        try {
            List<TestDatasetTypesInfo> testDataSetTypesInfoList = iTestDataSetTypesService.getTypeAll();
            if (testDataSetTypesInfoList.size() == 0) {
                return ServerResponse.createByErrorMessage("获取Type列表失败");
            }
            return ServerResponse.createBySuccess(testDataSetTypesInfoList);
        } catch (Exception e) {
            logger.error("获取Type列表失败： ", e);
            return ServerResponse.createByErrorMessage("获取Type列表失败");
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ServerResponse getType(@Validated @RequestBody Page page, BindingResult result) {
        logger.info("查询Type列表");
        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }

            TestDatasetTypesInfo testDatasetTypesInfo = JsonUtil.jsonToObject(JsonUtil.objToJson(page.getData()), TestDatasetTypesInfo.class);
            List<TestDatasetTypesInfo> testDataSetTypesInfoList = iTestDataSetTypesService.getTestDataSetType(testDatasetTypesInfo);
            int total = testDataSetTypesInfoList.size();
            Page p = Page.createPage(page.getCurrentPage(), page.getPageSize(), total, testDataSetTypesInfoList);

            if (testDataSetTypesInfoList.size() == 0) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.FOUND_RESULT.getCode(), ResponseCode.FOUND_RESULT.getDesc());
            }

            return ServerResponse.createBySuccess(p);
        } catch (Exception e) {
            logger.error("查询Type列表失败： ", e);
            return ServerResponse.createByErrorMessage("查询Type列表失败");
        }
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ServerResponse detailType(@PathVariable Integer id) {
        logger.info("查看Type详情");
        try {
            TestDatasetTypesInfo testDatasetTypesInfo = iTestDataSetTypesService.getTestDataSetTypeById(id);
            if (testDatasetTypesInfo == null) {
                return ServerResponse.createByErrorMessage("获取Type详情失败");
            }

            return ServerResponse.createBySuccess(testDatasetTypesInfo);
        } catch (Exception e) {
            logger.error("获取Type详情失败： ", e);
            return ServerResponse.createByErrorMessage("获取Type详情失败");
        }
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.PUT)
    public ServerResponse modifyType(@PathVariable Integer id, @RequestBody TestDatasetTypesInfo testDatasetTypesInfo) {
        logger.info("修改Type详情");
        try {
            String typeName = testDatasetTypesInfo.getName();
            if (!StringUtil.isEmpty(typeName) && !iTestDataSetTypesService.getTestDataSetTypeById(id).getName().equals(typeName)) {
                if (iTestDataSetTypesService.getTypeByName(typeName) != null) {
                    return ServerResponse.createByErrorMessage("Type名称已存在");
                }
            }

            testDatasetTypesInfo.setId(id);
            int i = iTestDataSetTypesService.updateTestDataSetType(testDatasetTypesInfo);
            TestDatasetTypesInfo type = iTestDataSetTypesService.getTestDataSetTypeById(id);

            if (i == 1 && type != null) {
                return ServerResponse.createBySuccess(type);
            }
            return ServerResponse.createByErrorMessage("修改Type详情失败");

        } catch (Exception e) {
            logger.error("修改Type详情失败： ", e);
            return ServerResponse.createByErrorMessage("修改Type详情失败");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ServerResponse deleteType(@PathVariable Integer id) {
        logger.info("删除Type");
        try {

            if (iTestDataSetTypesService.getTestDataSetTypeById(id) == null) {
                return ServerResponse.createByErrorMessage("Type id不存在");
            }

            if (iTestDataSetTypesService.deleteTestDataSetType(id) != 1) {
                return ServerResponse.createByErrorMessage("删除Type失败");
            }

            return ServerResponse.createBySuccessMessage("Type删除成功");
        } catch (Exception e) {
            logger.error("删除Type失败： ", e);
            return ServerResponse.createByErrorMessage("删除Type失败");
        }
    }

    @RequestMapping(value = "/batch_delete", method = RequestMethod.POST)
    public ServerResponse deleteTypeList(@RequestBody Map<String, Object> requestData) {
        logger.info("批量删除Type");
        try {
            List ids = (List) requestData.get("ids");
            if (iTestDataSetTypesService.getTypeByIdList(ids).size() != ids.size()) {
                return ServerResponse.createByErrorMessage("Type id不正确");
            }

            if (iTestDataSetTypesService.deleteByTypeIdList(ids) != ids.size()) {
                return ServerResponse.createByErrorMessage("删除Type失败");
            }

            return ServerResponse.createBySuccessMessage("Type删除成功");
        } catch (Exception e) {
            logger.error("批量删除Type失败： ", e);
            return ServerResponse.createByErrorMessage("批量删除Type失败");
        }
    }

}
