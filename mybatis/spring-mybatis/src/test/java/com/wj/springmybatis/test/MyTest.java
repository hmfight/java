package com.wj.springmybatis.test;

import com.wj.springmybatis.service.UserService;
import com.wj.springmybatis.model.TestUser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author : wangjia
 * @time : 2018/1/8 22:53
 */
public class MyTest {
    private ClassPathXmlApplicationContext context;

    @Before
    public void initSpringContext() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:context.xml");
    }

    @Test
    public void testGet() throws Exception {
        UserService userService = (UserService) context.getBean("userService");
        userService.get(1);
        userService.get(3);
        userService.get(4);
    }

    @Test
    public void testIns() throws Exception {
        UserService userService = (UserService) context.getBean("userService");
        TestUser testUser = new TestUser("t" + new Date().getTime() / 1000, 11, "email", 123213);
        userService.ins(testUser);
    }
}
