package com.wangjia.spring.lifecycle;

import lombok.Data;

/**
 * @author : wj
 * @date : 2021/5/31 00:09
 */
@Data
public class Address {
    private String code;

    @Override
    public String toString() {
        return "Address{" +
                "code='" + code + '\'' +
                '}';
    }
}
