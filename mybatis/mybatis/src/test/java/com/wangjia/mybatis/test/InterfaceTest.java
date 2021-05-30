package com.wangjia.mybatis.test;

import com.wangjia.mybatis.dao.UserDao;
import com.wangjia.mybatis.model.TestUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * @author : wangjia
 * @time : 2018/1/8 22:53
 */
public class InterfaceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterfaceTest.class);
    private SqlSession session;
    private UserDao userDao;

    @Before
    public void initSpringContext() throws Exception {
        String resource = "mybatis_conf.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        session = sessionFactory.openSession(true);
        userDao = session.getMapper(UserDao.class);
    }

    @Test
    public void get() throws Exception {
        TestUser testUser = userDao.getById(1);
        LOGGER.info("{}", testUser);
    }

    @Test
    public void getAll() throws Exception {
        LOGGER.info("{}", userDao.getAll());
    }

    @Test
    public void ins() throws Exception {
        TestUser testUser = new TestUser("test" + System.nanoTime(), 12, "email", 231L);
        int ins = userDao.ins(testUser);
        LOGGER.info("{}", ins);
    }

    @After
    public void after() throws Exception {
        session.close();
    }
}
