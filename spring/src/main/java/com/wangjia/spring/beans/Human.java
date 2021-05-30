package com.wangjia.spring.beans;

import com.wangjia.spring.annotation.ReturnLog;
import org.springframework.stereotype.Component;

/**
 * Created by wangjia on 16/3/2 17:01
 */
@Component
public class Human implements Sleepable {

    @ReturnLog()
    public String sleep() {
        System.out.println("print bt Human sleep method");
        return "i am returned by human's sleep method";
    }

}
