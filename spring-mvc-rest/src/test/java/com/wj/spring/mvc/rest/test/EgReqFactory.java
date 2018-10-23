package com.wj.spring.mvc.rest.test;

import org.springframework.web.bind.annotation.RequestMethod;
import sun.rmi.rmic.Names;

import java.util.HashMap;

/**
 * @author : wangjia_yql@qq.com
 */
@SuppressWarnings("WeakerAccess")
public class EgReqFactory {

    public static EgTestApiRequest regist(String username, String password) {
        HashMap<String, String> apiParams = new HashMap<>();
        apiParams.put("username", username);
        apiParams.put("password", password);
        String apiPath = "/user/regist";
        return new EgTestApiRequest(apiPath, RequestMethod.POST, apiParams);
    }

    public static EgTestApiRequest login(String username, String password) {
        HashMap<String, String> apiParams = new HashMap<>();
        apiParams.put("username", username);
        apiParams.put("password", password);
        String apiPath = "/user/login";
        return new EgTestApiRequest(apiPath, RequestMethod.POST, apiParams);
    }

    public static EgTestApiRequest getByNames(String namesJson) {
        HashMap<String, String> apiParams = new HashMap<>();
        apiParams.put("names", namesJson);
        String apiPath = "/user/all";
        return new EgTestApiRequest(apiPath, RequestMethod.POST, apiParams);
    }
}
