package com.wangjia.mybatis.test;

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
import java.util.List;

/**
 * @author : wangjia
 * @time : 2018/1/8 22:53
 */
public class SqlSessionTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SqlSessionTest.class);
    private SqlSession session;

    @Before
    public void initSpringContext() throws Exception {
        String resource = "mybatis_conf.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        session = sessionFactory.openSession(true);
    }

    @Test
    public void get() throws Exception {
        String statement = "com.wj.mybatis.dao.UserDao.getById";
        TestUser user = session.selectOne(statement, 1L);
        LOGGER.info("{}", user);
    }

    @Test
    public void getAll() throws Exception {
        String statement = "com.wj.mybatis.dao.UserDao.getAll";
        List<TestUser> users = session.selectList(statement);
        LOGGER.info("{}", users);
    }

    @Test
    public void ins() throws Exception {
        TestUser testUser = new TestUser("wangjia" + System.nanoTime(), 12, "email", 231L);
        String statement = "com.wj.mybatis.dao.UserDao.ins";//映射sql的标识字符串
        int ROW = session.insert(statement, testUser);
        LOGGER.info("{}", ROW);
    }

    @After
    public void after() throws Exception {
        session.close();
    }
}
