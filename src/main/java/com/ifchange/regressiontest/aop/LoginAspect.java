package com.ifchange.regressiontest.aop;

import com.ifchange.regressiontest.constant.Const;
import com.ifchange.regressiontest.constant.ResponseCode;
import com.ifchange.regressiontest.dto.ServerResponse;
import com.ifchange.regressiontest.model.Token;
import com.ifchange.regressiontest.model.User;
import com.ifchange.regressiontest.service.ITokenService;
import com.ifchange.regressiontest.service.IUserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: LoginAspect
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 9:40 2019/4/4
 */
@Component
@Aspect
public class LoginAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ITokenService iTokenService;
    @Autowired
    private IUserService iUserService;

    @Pointcut("within(com.ifchange.regressiontest.controller..*)&&!within(com.ifchange.regressiontest.controller.UserController)")
    // IndexController中写了登录方法
    public void login() {
    }

    /**
     * 前置通知：目标方法执行之前执行以下方法体的内容
     *
     * @param joinPoint
     */
    /*@Before("login()")
    public void doBefore(JoinPoint joinPoint) {
    }*/

    /**
     * 后置通知：目标方法执行之后执行以下方法体的内容，不管是否发生异常。
     *
     * @param joinPoint
     */
    /*@After("login()")
    public void doAfter(JoinPoint joinPoint) {

    }*/

    /**
     * 返回通知：目标方法正常执行完毕时执行以下代码
     *
     * @param result
     */
    /*@AfterReturning(returning = "result", pointcut = "login()")
    public void doAfterReturning(Object result) {

    }*/

    /**
     * 异常通知：目标方法发生异常的时候执行以下代码
     *
     * @param jp
     * @param e
     */
    /*@AfterThrowing
    public void afterThrowMethod(JoinPoint jp, NullPointerException e) {
    }*/

    /**
     * 环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("login()")
    public Object trackInfo(ProceedingJoinPoint pjp) throws Throwable {

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra != null) {
            HttpServletRequest request = sra.getRequest();

            String token = request.getHeader(Const.TOKEN);
            if (token == null) {
                logger.info("token为空");
                return ServerResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), ResponseCode.UNLOGIN.getDesc());
            }

            Token tokenEntity = iTokenService.getToken(token);

            if (tokenEntity == null) {
                logger.info("token失效");
                return ServerResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), ResponseCode.UNLOGIN.getDesc());
            }

            //查询用户信息
            User user = iUserService.getUserById(tokenEntity.getUserId());
            if (user == null) {
                logger.info("用户不存在");
                return ServerResponse.createByErrorCodeMessage(ResponseCode.UNLOGIN.getCode(), ResponseCode.UNLOGIN.getDesc());
            }
            return pjp.proceed();
        }
        return ServerResponse.createByError();

    }
}
