package com.wj.spring.mvc.rest.service;

import com.wj.spring.mvc.rest.model.User;

import java.util.List;

/**
 * @author : wangjia
 * @time : 2017/12/25 22:56
 */
public interface UserService {
    void regist(String username, String password);

    User login(String username, String password);

    List<User> getAll();

}
