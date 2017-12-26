package com.wj.spring.mvc.rest.test;

import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * @author : wangjia_yql@qq.com
 */
public class EgTestApiRequest {
    private String apiPath;
    private RequestMethod requestMethod;
    private HashMap<String, String> params = new HashMap<>(10);
    private HashMap<String, String> headers = new HashMap<>(10);

    public EgTestApiRequest(String apiPath, RequestMethod requestMethod, HashMap<String, String> params) {
        this.apiPath = apiPath;
        this.requestMethod = requestMethod;
        this.params = params;
    }

    public EgTestApiRequest(String apiPath, RequestMethod requestMethod, HashMap<String, String> params, HashMap<String, String> headers) {
        this.apiPath = apiPath;
        this.requestMethod = requestMethod;
        this.params = params;
        this.headers = headers;
    }

    public String getApiPath() {
        return apiPath;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

}
