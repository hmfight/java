package com.wangjia.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by wangjia on 16/3/2 22:07
 */
@Aspect
@Component
public class SysLogManage {

    @Pointcut("@annotation(com.wangjia.spring.annotation.ReturnLog)")
    public void log() {
    }

    @Before(value = "log()",argNames = "")
    public void before(JoinPoint joinPoint) {
        System.out.println("before annotation log beans");
    }

    @After("log()")
    public void after() {
        System.out.println("after annotation log beans");
    }

    @AfterReturning(value = "log()", returning = "res")
    public void afterReturn(String res) {
        System.out.println("after return : " + res);
    }

    /*@Around("log()")
    public void mm1(String res) {
        System.out.println("after return : " + res);
    }*/
}


