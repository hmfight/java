package com.wangjia.spring.lifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangjia on 16/3/2 15:16
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:lifecycle-conext.xml");
        System.out.println("before getBean");
        context.getBean("petUser");
        System.out.println("after getBean");
        context.close();
    }

}
