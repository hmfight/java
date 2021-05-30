package com.wangjia.springmybatisrw.dao;

import com.wangjia.springmybatis.model.TestUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author : wangjia
 * @time : 2017/12/25 23:15
 */
public interface TestUserDao {

    TestUser getById(@Param("id") long id);

    TestUser getAll();

    int ins(TestUser testUser);
}
