package com.wj.spring.mvc.rest.test.util;

import com.wj.spring.mvc.rest.test.EgTestApiRequest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;
import java.util.Set;

import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * @author : wangjia_yql@qq.com
 */
@SuppressWarnings("WeakerAccess")
public class EgTestCase {
    private EgTestApiRequest egTestApiRequest;
    private MockMvc mockMvc;
    private int expectCode;

    public EgTestCase(EgTestApiRequest egTestApiRequest, MockMvc mockMvc, int expectCode) {
        this.egTestApiRequest = egTestApiRequest;
        this.mockMvc = mockMvc;
        this.expectCode = expectCode;
    }

    public RequestMethod getApiMethod() {
        return egTestApiRequest.getRequestMethod();
    }

    public String getApiPath() {
        return egTestApiRequest.getApiPath();
    }

    public Map<String, String> getApiParams() {
        return egTestApiRequest.getParams();
    }

    public EgTestApiRequest getEgTestApiRequest() {
        return egTestApiRequest;
    }

    public String run() throws Exception {
        String response = execMockHttp();
        int actualCode = ResponseUtils.getCode(response);
        assertEquals("code", expectCode, actualCode);
        return response;
    }

    private String execMockHttp() throws Exception {
        MockHttpServletRequestBuilder request = buildRequest();
        MvcResult mvcResult = mockMvc.perform(request).andDo(MockMvcResultHandlers.print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals("http status", HttpStatus.OK.value(), status);
        return mvcResult.getResponse().getContentAsString();
    }

    private MockHttpServletRequestBuilder buildRequest() {
        MockHttpServletRequestBuilder request;
        if (RequestMethod.GET.equals(getApiMethod())) {
            request = MockMvcRequestBuilders.get(getApiPath());
        } else {
            request = MockMvcRequestBuilders.post(getApiPath());
        }
        Map<String, String> apiParamsMap = getApiParams();
        Set<Map.Entry<String, String>> entries = apiParamsMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            request = request.param(entry.getKey(), entry.getValue());
        }
        Map<String, String> headers = getEgTestApiRequest().getHeaders();
        Set<String> headerKeySet = headers.keySet();
        for (String key : headerKeySet) {
            request = request.header(key, headers.get(key));
        }
        return request;
    }
}
