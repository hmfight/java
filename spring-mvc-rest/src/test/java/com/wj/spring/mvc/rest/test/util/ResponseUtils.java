package com.wj.spring.mvc.rest.test.util;

import com.alibaba.fastjson.JSON;
import com.wj.spring.mvc.rest.util.EgWebUtils;

import java.util.List;

public class ResponseUtils {

    public static int getCode(String jsonStr) {
        String result = JSON.parseObject(jsonStr).get(EgWebUtils.CodeKey).toString();
        return Integer.valueOf(result);
    }

    public static <T> T getByType(String res, String key, Class<T> t) {
        String result = JSON.parseObject(res).get(EgWebUtils.PayloadKey).toString();
        String tokenStr = JSON.parseObject(result).get(key).toString();
        return JSON.parseObject(tokenStr, t);
    }

    public static <T> List<T> getListByType(String res, String key, Class<T> t) {
        String result = JSON.parseObject(res).get(EgWebUtils.PayloadKey).toString();
        String tokenStr = JSON.parseObject(result).get(key).toString();
        return JSON.parseArray(tokenStr, t);
    }

    public static String getResString(String res, String key) {
        String result = JSON.parseObject(res).get(EgWebUtils.PayloadKey).toString();
        return JSON.parseObject(result).get(key).toString();
    }

    public static String getReason(String res) {
        String result = JSON.parseObject(res).get(EgWebUtils.ReasonKey).toString();
        return result;
    }

}
