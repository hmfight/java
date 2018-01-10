package com.wj.springmybatis.service;

import com.wj.springmybatis.dao.TestUserDao;
import com.wj.springmybatis.model.TestUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : wangjia
 * @time : 2018/1/10 10:36
 */
public class UserServiceImpl implements UserService {

    private final TestUserDao userDao;

    @Autowired
    public UserServiceImpl(TestUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public TestUser get(long id) {
        return userDao.getById(id);
    }

    @Override
    public void ins(TestUser user) {
        userDao.ins(user);
    }
}
