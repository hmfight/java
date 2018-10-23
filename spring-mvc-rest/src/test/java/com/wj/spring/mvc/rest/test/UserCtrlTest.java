package com.wj.spring.mvc.rest.test;

import com.wj.spring.mvc.rest.test.util.EgTestCase;
import com.wj.spring.mvc.rest.test.util.EgTestCaseV1;
import com.wj.spring.mvc.rest.test.util.EgTestUtils;
import com.wj.spring.mvc.rest.test.util.ResponseUtils;
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
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
    public void testUserRegisterAndLoginV() throws Exception {
        MvcResult registOk = mockMvc.perform(
                post("/user/regist")
                        .param("username", "loginwest")
                        .param("password", "111111")
        )
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String registOkRes = registOk.getResponse().getContentAsString();
        AssertionErrors.assertEquals("code", EnumCommonErrorCode.SUCCESS.getCode(), ResponseUtils.getCode(registOkRes));

        MvcResult registDup = mockMvc.perform(post("/user/regist")
                .param("username", "loginwest")
                .param("password", "111111")
        )
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String registDupRes = registDup.getResponse().getContentAsString();
        AssertionErrors.assertEquals("code", EnumUserErrorCode.NAME_EXIST.getCode(), ResponseUtils.getCode(registDupRes));

        MvcResult loginNoExist = mockMvc.perform(post("/user/login")
                .param("username", "noexist")
                .param("password", "111111")
        )
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String loginNoExistRes = loginNoExist.getResponse().getContentAsString();
        AssertionErrors.assertEquals("code", EnumUserErrorCode.NAME_OR_PW_ERROR.getCode(), ResponseUtils.getCode(loginNoExistRes));

        MvcResult pwError = mockMvc.perform(post("/user/login")
                .param("username", "loginwest")
                .param("password", "222222")
        )
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String pwErrorRes = pwError.getResponse().getContentAsString();
        AssertionErrors.assertEquals("code", EnumUserErrorCode.NAME_OR_PW_ERROR.getCode(), ResponseUtils.getCode(pwErrorRes));


        MvcResult loginOk = mockMvc.perform(post("/user/login")
                .param("username", "loginwest")
                .param("password", "111111")
        )
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String loginOkRes = loginOk.getResponse().getContentAsString();
        AssertionErrors.assertEquals("code", EnumCommonErrorCode.SUCCESS.getCode(), ResponseUtils.getCode(loginOkRes));
    }

    @Test
    public void testUserRegisterAndLoginV1() throws Exception {
        String realLoginName = "loginwest";

        EgTestCaseV1 registOk = ApiTestCaseFactoryV1.regist
                (realLoginName, "111111", EnumCommonErrorCode.SUCCESS);
        EgTestUtils.runCase(registOk, mockMvc);

        EgTestCaseV1 nameExist = ApiTestCaseFactoryV1.regist(realLoginName, "111111", EnumUserErrorCode.NAME_EXIST);
        EgTestUtils.runCase(nameExist, mockMvc);

        EgTestCaseV1 noexist = ApiTestCaseFactoryV1.login("noexist", "111111", EnumUserErrorCode.NAME_OR_PW_ERROR);
        EgTestUtils.runCase(noexist, mockMvc);

        EgTestCaseV1 pwError = ApiTestCaseFactoryV1.login(realLoginName, "222222", EnumUserErrorCode.NAME_OR_PW_ERROR);
        EgTestUtils.runCase(pwError, mockMvc);

        EgTestCaseV1 success = ApiTestCaseFactoryV1.login(realLoginName, "111111", EnumCommonErrorCode.SUCCESS);
        String loginRes = EgTestUtils.runCase(success, mockMvc);

        AssertionErrors.assertEquals("loginName", realLoginName, ResponseUtils.getResCycle(loginRes, "user", "username"));
    }

    @Test
    public void testUserRegisterAndLoginV2() throws Exception {
        String realLoginName = "loginwest";
        apiTestCaseFactory.regist(realLoginName, "111111", EnumCommonErrorCode.SUCCESS).run();
        apiTestCaseFactory.regist(realLoginName, "111111", EnumUserErrorCode.NAME_EXIST).run();
        apiTestCaseFactory.login("noexist", "111111", EnumUserErrorCode.NAME_OR_PW_ERROR).run();
        String loginRes = apiTestCaseFactory.login(realLoginName, "111111", EnumCommonErrorCode.SUCCESS).run();
        AssertionErrors.assertEquals("loginName", realLoginName, ResponseUtils.getResCycle(loginRes, "user", "username"));
        apiTestCaseFactory.login(realLoginName, "222222", EnumUserErrorCode.NAME_OR_PW_ERROR).run();
    }

    @Test
    public void testUserRegisterAndLogin3() throws Exception {
        String realLoginName = "loginwest";
        new EgTestCase(mockMvc).request(EgReqFactory.regist(realLoginName, "111111"))
                .expect(EnumCommonErrorCode.SUCCESS)
                .run();
        new EgTestCase(mockMvc).request(EgReqFactory.regist(realLoginName, "111111"))
                .expect(EnumUserErrorCode.NAME_EXIST)
                .run();
        new EgTestCase(mockMvc).request(EgReqFactory.login("noexist", "111111"))
                .expect(EnumUserErrorCode.NAME_OR_PW_ERROR)
                .run();
        new EgTestCase(mockMvc).request(EgReqFactory.login(realLoginName, "12312312"))
                .expect(EnumUserErrorCode.NAME_OR_PW_ERROR)
                .run();
        String loginRes = new EgTestCase(mockMvc).request(EgReqFactory.login(realLoginName, "111111"))
                .expect(EnumCommonErrorCode.SUCCESS)
                .run();
        AssertionErrors.assertEquals("loginName", realLoginName, ResponseUtils.getResCycle(loginRes, "user", "username"));
    }

    @Test
    public void testGetAll() throws Exception {
        new EgTestCase(mockMvc).request(EgReqFactory.regist("wangjia1", "111111"))
                .expect(EnumCommonErrorCode.SUCCESS)
                .run();
        new EgTestCase(mockMvc).request(EgReqFactory.regist("wangjia2", "111111"))
                .expect(EnumCommonErrorCode.SUCCESS)
                .run();
        new EgTestCase(mockMvc).request(EgReqFactory.getall())
                .expect(EnumCommonErrorCode.SUCCESS)
                .run();
    }

}

