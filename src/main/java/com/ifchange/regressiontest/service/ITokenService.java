package com.ifchange.regressiontest.service;


import com.ifchange.regressiontest.model.Token;

/**
 * @ClassName: ITokenService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 13:59 2019/1/11
 */
public interface ITokenService {
    /**
     * 创建一个token关联上指定用户
     *
     * @param userId
     * @return
     */
    Token createToken(Integer userId);

    /**
     * 从字符串中解析token
     *
     * @param authentication 加密后的字符串
     * @return
     */
    Token getToken(String authentication);

    void deleteToken(String authentication);

}
