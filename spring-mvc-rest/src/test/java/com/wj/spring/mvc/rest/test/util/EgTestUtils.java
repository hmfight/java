package com.wj.spring.mvc.rest.test.util;

import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * author  : wangjia
 * time    : 2017/1/12 18:11
 * contact : wangjia_yql@qq.com
 * desc    :
 */
public class EgTestUtils {

    public static String runCase(EgTestCaseV1 testCase, MockMvc mockMvc) throws Exception {
        String apiReturn = EgTestUtils.getResponse(testCase, mockMvc);
        int actualCode = ResponseUtils.getCode(apiReturn);
        assertEquals("not =", testCase.getExpectCode(), actualCode);
        return apiReturn;
    }

    public static String getResponse(EgTestCaseV1 testCase, MockMvc mockMvc) throws Exception {
        MockHttpServletRequestBuilder request;
        if (RequestMethod.GET.equals(testCase.getApiMethod())) {
            request = MockMvcRequestBuilders.get(testCase.getApiPath());
        } else {
            request = MockMvcRequestBuilders.post(testCase.getApiPath());
        }
        Map<String, String> apiParamsMap = testCase.getApiParams();
        Set<String> keySet = apiParamsMap.keySet();
        for (String key : keySet) {
            request = request.param(key, apiParamsMap.get(key));
        }
        Map<String, String> headers = testCase.getEgTestApiRequest().getHeaders();
        Set<String> headerKeySet = headers.keySet();
        for (String key : headerKeySet) {
            request = request.header(key, headers.get(key));
        }

        MvcResult mvcResult = mockMvc.perform(request).andDo(MockMvcResultHandlers.print()).andReturn();
        return mvcResult.getResponse().getContentAsString();
    }

    public static void runSqlScripts(WebApplicationContext wac, JdbcTemplate jdbcTemplate, String... sqlScriptPaths) {
        for (String sqlScriptPath : sqlScriptPaths) {
            Resource resource = wac.getResource(sqlScriptPath);
            JdbcTestUtils.executeSqlScript(jdbcTemplate, resource, true);
        }
    }

}
