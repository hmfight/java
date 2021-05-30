package com.wangjia.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by wangjia on 16/3/2 15:40
 */
@Aspect
@Component
public class GetMethodCostTime {
    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间

    @Pointcut("execution(* *.sleep(..))")
    public void print(){}

    @Before("print()")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        startTimeMillis = System.currentTimeMillis();
        System.out.println(startTimeMillis);
    }

    @After("print()")
    public void doAfterInServiceLayer(JoinPoint joinPoint) {
        endTimeMillis = System.currentTimeMillis();
        System.out.println(endTimeMillis);
        System.out.println("this method cost" + (endTimeMillis - startTimeMillis)/1000.0 +"s");
    }

}

