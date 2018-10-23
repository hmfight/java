package com.wj.spring.mvc.rest.service;

import com.wj.spring.mvc.rest.dao.UserDao;
import com.wj.spring.mvc.rest.model.User;
import com.wj.spring.mvc.rest.util.EgException;
import com.wj.spring.mvc.rest.util.EnumUserErrorCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author : wangjia
 * @time : 2017/12/25 23:14
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @param password 简单demo,密码就不加密了
     */
    @Override
    public void regist(String username, String password) {
        checkUserName(username);
        userDao.ins(new User(username, password));
    }

    private void checkUserName(String username) {
        if (userDao.existName(username)) {
            throw new EgException(EnumUserErrorCode.NAME_EXIST);
        }
    }

    @Override
    public User login(String username, String password) {
        return userDao.getByNamedParameter(username, password).orElseThrow(
                () -> new EgException(EnumUserErrorCode.NAME_OR_PW_ERROR)
        );
    }

    @Override
    public List<User> getByNames(List<String> names) {
        return userDao.getAll(names);
    }
}