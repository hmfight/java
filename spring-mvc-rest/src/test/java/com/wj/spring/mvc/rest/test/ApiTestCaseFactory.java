package com.wj.spring.mvc.rest.test;

import com.wj.spring.mvc.rest.test.util.EgTestCase;
import com.wj.spring.mvc.rest.util.IEgReturnCode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * @author : wangjia_yql@qq.com
 */
@SuppressWarnings("WeakerAccess")
public class ApiTestCaseFactory {
    private MockMvc mockMvc;

    public ApiTestCaseFactory(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public EgTestCase regist(String username, String password, IEgReturnCode egReturnCode) {
        HashMap<String, String> apiParams = new HashMap<>();
        apiParams.put("username", username);
        apiParams.put("password", password);
        String apiPath = "/user/regist";
        EgTestApiRequest egTestApiRequest = new EgTestApiRequest(apiPath, RequestMethod.POST, apiParams);
        return new EgTestCase(egTestApiRequest, mockMvc, egReturnCode.getCode());
    }

    public EgTestCase login(String username, String password, IEgReturnCode egReturnCode) {
        HashMap<String, String> apiParams = new HashMap<>();
        apiParams.put("username", username);
        apiParams.put("password", password);
        String apiPath = "/user/login";
        EgTestApiRequest egTestApiRequest = new EgTestApiRequest(apiPath, RequestMethod.POST, apiParams);
        return new EgTestCase(egTestApiRequest, mockMvc, egReturnCode.getCode());
    }


}
