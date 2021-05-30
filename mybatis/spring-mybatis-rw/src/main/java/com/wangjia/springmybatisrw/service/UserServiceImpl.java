package com.wangjia.springmybatisrw.service;

import com.wangjia.springmybatis.model.TestUser;
import com.wangjia.springmybatis.service.UserService;
import com.wangjia.springmybatisrw.dao.TestUserDao;
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
