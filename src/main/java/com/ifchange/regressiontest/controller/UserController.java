package com.ifchange.regressiontest.controller;

import com.ifchange.regressiontest.constant.Const;
import com.ifchange.regressiontest.constant.ResponseCode;
import com.ifchange.regressiontest.dto.ServerResponse;
import com.ifchange.regressiontest.model.Token;
import com.ifchange.regressiontest.model.User;
import com.ifchange.regressiontest.service.ITokenService;
import com.ifchange.regressiontest.service.IUserService;
import com.ifchange.regressiontest.util.ValidataUtil;
import com.ifchange.regressiontest.validator.order.Create;
import com.ifchange.regressiontest.validator.order.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: UserController
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 15:13 2019/4/2
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService iUserService;
    @Autowired
    ITokenService iTokenService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ServerResponse userLogin(HttpServletResponse response, @Validated({Get.class}) @RequestBody User user, BindingResult result) {
        logger.info("登录");
        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }

            if (iUserService.getUser(user) != null) {
                //生成token
                User u = iUserService.getUserByUsername(user.getUsername());
                Integer userId = u.getId();
                Token token = iTokenService.createToken(userId);
                response.setHeader(Const.TOKEN, token.getToken());

                return ServerResponse.createBySuccessMessage("登录成功");
            }
            return ServerResponse.createByErrorMessage("登录失败");
        } catch (Exception e) {
            logger.error("登录失败： ", e);
            return ServerResponse.createByErrorMessage("登录失败");
        }
    }

    @RequestMapping(value = "/current_user", method = RequestMethod.GET)
    public ServerResponse getCurrentUser(HttpServletRequest request){
        try {
            String token = request.getHeader(Const.TOKEN);
            Token tokenEntity = iTokenService.getToken(token);
            User user = iUserService.getUserById(tokenEntity.getUserId());
            return  ServerResponse.createBySuccess(user);
        } catch (Exception e) {
            logger.error("获取CurrentUser失败： ", e);
            return ServerResponse.createByErrorMessage("获取CurrentUser失败");
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ServerResponse userCreate(@Validated({Create.class}) @RequestBody User user, BindingResult result) {
        logger.info("创建用户");

        try {
            String dataResult = ValidataUtil.judgeValidata(result);
            if (!dataResult.equals(ResponseCode.PARAM_CORRECT.getDesc())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_INCORRECT.getCode(), ResponseCode.PARAM_INCORRECT.getDesc(), dataResult);
            }

            if (iUserService.getUserByUsername(user.getUsername()) != null) {
                return ServerResponse.createByErrorMessage("用户名已存在");
            }

            if (iUserService.createUser(user) == 1) {
                return ServerResponse.createBySuccessMessage("用户创建成功");
            } else {
                return ServerResponse.createByErrorMessage("创建用户失败");
            }
        } catch (Exception e) {
            logger.error("创建用户失败： ", e);
            return ServerResponse.createByErrorMessage("创建用户失败");
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ServerResponse userLogout(HttpServletRequest request) {
        logger.info("退出");
        try {
            String token = request.getHeader(Const.TOKEN);
            if (token != null) {
                iTokenService.deleteToken(token);
            }
            return ServerResponse.createBySuccess("退出成功");

        } catch (Exception e) {
            logger.error("退出失败： ", e);
            return ServerResponse.createByErrorMessage("退出失败");
        }
    }

}
