package com.wj.spring.mvc.rest.test;

import com.wj.spring.mvc.rest.test.util.EgTestCaseV1;
import com.wj.spring.mvc.rest.util.IEgReturnCode;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * @author : wangjia_yql@qq.com
 */
@SuppressWarnings("WeakerAccess")
public class ApiTestCaseFactoryV1 {

    public static EgTestCaseV1 regist(String username, String password, IEgReturnCode egReturnCode) {
        HashMap<String, String> apiParams = new HashMap<>();
        apiParams.put("username", username);
        apiParams.put("password", password);
        String apiPath = "/user/regist";
        EgTestApiRequest egTestApiRequest = new EgTestApiRequest(apiPath, RequestMethod.POST, apiParams);
        return new EgTestCaseV1(egTestApiRequest, egReturnCode.getCode());
    }

    public static EgTestCaseV1 login(String username, String password, IEgReturnCode egReturnCode) {
        HashMap<String, String> apiParams = new HashMap<>();
        apiParams.put("username", username);
        apiParams.put("password", password);
        String apiPath = "/user/login";
        EgTestApiRequest egTestApiRequest = new EgTestApiRequest(apiPath, RequestMethod.POST, apiParams);
        return new EgTestCaseV1(egTestApiRequest, egReturnCode.getCode());
    }

}
