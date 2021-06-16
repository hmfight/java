package com.wangjia.spring;

import com.wangjia.spring.beans.Human;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangjia on 16/3/2 15:16
 */
public class Main {

    public static void main(String[] args) {
        method("classpath:aop-hand-context.xml");
    }

    public static void test2() {
        String contextpath = "classpath:aop-hand-context.xml";
        method(contextpath);
    }

    public static void method(String contextPath) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(contextPath);
            Human human = (Human) context.getBean("human");
            human.sleep();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

}
