package com.ifchange.regressiontest.service.impl;

import com.ifchange.regressiontest.model.Token;
import com.ifchange.regressiontest.util.TokenGenerator;
import com.ifchange.regressiontest.service.ITokenService;
import com.ifchange.regressiontest.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TokenServiceImpl
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 14:02 2019/1/11
 */
@Service("iTokenService")
public class TokenServiceImpl implements ITokenService {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Token createToken(Integer userId) {
        //使用uuid作为源token
        String token = TokenGenerator.generateValue();
        Token t = new Token(userId, token);
        //存储到redis并设置过期时间
        redisUtil.set(token, t);
        return t;
    }

    @Override
    public Token getToken(String token) {
        return redisUtil.get(token, Token.class);
    }

    @Override
    public void deleteToken(String token) {
        redisUtil.delete(token);
    }


}
