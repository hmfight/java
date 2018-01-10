package com.wj.mybatis.dao;

import com.wj.mybatis.model.TestUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : wangjia
 * @time : 2017/12/25 23:15
 */
public interface UserDao {

    int ins(TestUser user);

    TestUser getById(@Param("id") long id);

    List<TestUser> getAll();

    TestUser getById2(@Param("id") long id);
}
