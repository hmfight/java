package com.wangjia.spring.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by wangjia on 16/3/2 17:02
 */
@Aspect
public class SleepHelper {

    @Pointcut("execution(* com.wangjia.spring.beans.Human.sleep(..))")
    public void sleeppoint() {
    }

    @Before("sleeppoint()")
    public void beforeSleep() {
        System.out.println("睡觉前要脱衣服!");
    }


    @AfterReturning("sleeppoint()")
    public void afterSleep() {
        System.out.println("睡醒了要穿衣服！");
    }



}
