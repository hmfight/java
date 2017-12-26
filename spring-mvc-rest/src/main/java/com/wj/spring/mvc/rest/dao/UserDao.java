package com.wj.spring.mvc.rest.dao;

import com.wj.spring.mvc.rest.model.User;

import java.util.Optional;

/**
 * @author : wangjia
 * @time : 2017/12/25 23:15
 */
public interface UserDao {
    void ins(User user);

    boolean existName(String username);

    Optional<User> get(String username, String password);
}
