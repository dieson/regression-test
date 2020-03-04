package com.ifchange.regressiontest.ServiceTest;

import com.ifchange.regressiontest.model.User;
import com.ifchange.regressiontest.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: UserService
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 15:14 2019/4/3
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserService {
    @Autowired
    IUserService iUserService;

    @Test
    public void testCreate() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test123");
        user.setEmail("ran.zuo@ifchange.com");
        int i = iUserService.createUser(user);
        System.out.println(i);
    }

    @Test
    public void testDelete() {
        int i = iUserService.deleteUser(1);
        System.out.println(i);
    }
}
