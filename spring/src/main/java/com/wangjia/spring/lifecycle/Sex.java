package com.wangjia.spring.lifecycle;

import lombok.Data;

/**
 * @author : wj
 * @date : 2021/5/31 00:09
 */
@Data
public class Sex {
    private int sex = 1;

    @Override
    public String toString() {
        return "Sex{" +
                "sex=" + sex +
                '}';
    }
}
