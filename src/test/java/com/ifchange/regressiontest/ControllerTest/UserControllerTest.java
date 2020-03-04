package com.ifchange.regressiontest.ControllerTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * @ClassName: UserControllerTest
 * @Description:
 * @author: Dieson Zuo
 * @date: Created in 15:33 2019/4/3
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {
    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    /**
     * 初始化 MVC 的环境
     */
    @Before
    public void before() {
        //单个类,项目拦截器无效
//        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
        //项目拦截器有效
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void createUser() {
        JSONObject user = new JSONObject();
        user.put("username", "test");
        user.put("password", "test123");
        user.put("email", "ran.zuo@ifchange.com");

        try {
            RequestBuilder request = MockMvcRequestBuilders.post("/user/create").contentType(MediaType.APPLICATION_JSON_UTF8).content(user.toJSONString());

            MvcResult mvcResult = mockMvc.perform(request).andReturn();
            int status = mvcResult.getResponse().getStatus();
            String content = mvcResult.getResponse().getContentAsString();
            System.out.println("返回结果：" + status);
            System.out.println("ResponseData：" + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void logout(){
        try {
            RequestBuilder request = MockMvcRequestBuilders.get("/user/logout");

            MvcResult mvcResult = mockMvc.perform(request).andReturn();
            int status = mvcResult.getResponse().getStatus();
            String content = mvcResult.getResponse().getContentAsString();
            System.out.println("返回结果：" + status);
            System.out.println("ResponseData：" + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
