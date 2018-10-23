package com.wj.springmybatis.service;


import com.wj.springmybatis.model.TestUser;

/**
 * @author : wangjia_yql@qq.com
 * @time : 2017/12/25 22:40
 */

public interface UserService {

    TestUser get(long id);

    void ins(TestUser user);
}
