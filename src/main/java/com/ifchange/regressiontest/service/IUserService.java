package com.ifchange.regressiontest.service;

import com.ifchange.regressiontest.model.User;

/**
 * @ClassName: IUserService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 14:38 2019/4/2
 */
public interface IUserService {
    int createUser(User user);

    int deleteUser(Integer id);

    int updateUser(User user);

    User getUser(User user);

    User getUserByUsername(String username);

    User getUserById(Integer id);

}
