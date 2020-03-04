package com.ifchange.regressiontest.service.impl;

import com.ifchange.regressiontest.dao.UserMapper;
import com.ifchange.regressiontest.model.User;
import com.ifchange.regressiontest.service.IUserService;
import com.ifchange.regressiontest.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 14:40 2019/4/2
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public int createUser(User user) {
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        return userMapper.insertSelective(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateUser(User user) {
        if (user.getPassword() != null) {
            user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        }
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User getUser(User user) {
        if (user.getPassword() != null) {
            user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        }
        return userMapper.selectSelective(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
