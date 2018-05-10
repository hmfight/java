package com.wj.spring.mvc.rest.test.util;

import com.wj.spring.mvc.rest.test.EgTestApiRequest;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author : wangjia_yql@qq.com
 */
@SuppressWarnings("WeakerAccess")
public class EgTestCaseV1 {
    private EgTestApiRequest egTestApiRequest;
    private int expectCode;

    public EgTestCaseV1(EgTestApiRequest egTestApiRequest, int expectCode) {
        this.egTestApiRequest = egTestApiRequest;
        this.expectCode = expectCode;
    }

    public RequestMethod getApiMethod() {
        return egTestApiRequest.getRequestMethod();
    }

    public int getExpectCode() {
        return expectCode;
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

    public void setEgTestApiRequest(EgTestApiRequest egTestApiRequest) {
        this.egTestApiRequest = egTestApiRequest;
    }
}
