package com.wj.spring.mvc.rest.test;

import com.wj.spring.mvc.rest.test.util.EgTestUtils;
import com.wj.spring.mvc.rest.util.EnumCommonErrorCode;
import com.wj.spring.mvc.rest.util.EnumUserErrorCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author : wangjia
 * @time : 2017/12/26 18:07
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:context.xml"})
public class UserCtrlTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCtrlTest.class);

    private MockMvc mockMvc;
    private ApiTestCaseFactory apiTestCaseFactory;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setMockMvcAndFac() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        apiTestCaseFactory = new ApiTestCaseFactory(mockMvc);
    }

    @Before
    public void dbInit() {
        String createSql = "classpath:sql/ds-schema.sql";
        EgTestUtils.runSqlScripts(wac, jdbcTemplate, createSql);
    }

    @Test
    public void testContextStart() throws Exception {
        LOGGER.info("context started successfully!");
    }

    @Test
    public void testUserRegisterAndLogin() throws Exception {
        apiTestCaseFactory.regist("loginwest", "111111", EnumCommonErrorCode.SUCCESS).run();
        apiTestCaseFactory.regist("loginwest", "111111", EnumUserErrorCode.NAME_EXIST).run();
        apiTestCaseFactory.login("noexist", "111111", EnumUserErrorCode.NAME_OR_PW_ERROR).run();
        String loginRes = apiTestCaseFactory.login("loginwest", "111111", EnumCommonErrorCode.SUCCESS).run();
        LOGGER.info(loginRes);
        apiTestCaseFactory.login("loginwest", "222222", EnumUserErrorCode.NAME_OR_PW_ERROR).run();
    }

}

