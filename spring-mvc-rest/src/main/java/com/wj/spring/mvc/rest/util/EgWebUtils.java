package com.wj.spring.mvc.rest.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * author  : wangjia
 * time    : 16/8/27 23:11
 * contact : wangjia_yql@qq.com
 */
public class EgWebUtils {
    private static final Logger logger = LoggerFactory.getLogger(EgWebUtils.class);

    private static final String SUCCESS_RES = "{\"code\":0,\"reason\":\"success\"}";
    private static final String SERVER_ERROR = "{\"code\":-1,\"reason\":\"unkown server error\"}";
    private static final String EMPTY_RES = "{\"code\":-7,\"reason\":\"empty res\"}";

    public static final int SUCCESS_CODE = 0;
    private static final int ERROR_CODE = 10001;
    public static final int ERROR_CODE_NO_USER = 2;
    public static final int OVERCALLFREQUENCY = 10002;

    private static final String resultNullReason = "not found";
    public static final String successReason = "success";

    static final Object[] emptyArray = new Object[0];
    static final Object emptyObject = new Object();

    static {
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
    }

    public static final String ReasonKey = "reason";
    public static final String CodeKey = "code";
    public static final String PayloadKey = "result";

    public static String createResponseJSON(final String reason, final int code, final Object payload) {
        return JSON.toJSONString(getHashMap(reason, code, payload));
    }

    // fast way to build error json
    private static String createErrorJSON(String errorMessage, int code) {
        return "{\"" + ReasonKey + "\": \"" + errorMessage + "\", \"" + CodeKey + "\":" + code + "}";
    }

    public static String createErrorJSON(String errorMessage) {
        return createErrorJSON(errorMessage, ERROR_CODE);
    }

    public static String createNullResultJSON() {
        return createErrorJSON(resultNullReason);
    }

    public static String createCommonResultJSON(Map<String, Object> result) {
        if (null != result) {
            return createResponseJSON(null, SUCCESS_CODE, result);
        }
        return createNullResultJSON();
    }

    public static String createCommonResultJSON(final String key, final Object result) {
        if (null != result) {
            HashMap<String, Object> map = new HashMap<String, Object>() {{
                put(key, result);
            }};
            return createResponseJSON(null, SUCCESS_CODE, map);
        } else {
            return EMPTY_RES;
        }
//        return createNullResultJSON();
    }

    public static String createResultJsonByEnum(IEgReturnCode code) {
        return createErrorJSON(code.getReason(), code.getCode());
    }

    public static String createServerErrorJson() {
        return createResultJsonByEnum(EnumCommonErrorCode.SERVER_ERROR);
    }

    public static String createParamErrorJson(String extraInfo) {
        return createResultJsonByEnum(EnumCommonErrorCode.PARAM_ERROR, extraInfo);
    }

    public static String createResultJsonByEnum(IEgReturnCode code, String extraInfo) {
        if (StringUtils.isBlank(extraInfo)) {
            return createResultJsonByEnum(code);
        }
        return createErrorJSON(code.getReason() + " [" + extraInfo + "]", code.getCode());
    }

    public static String createResultJson(EgException egException) {
        return createResultJsonByEnum(egException.getReturnCode(), egException.getExtraMessage());
    }

    /**
     * fast build success json
     */
    public static String createSuccessJSON() {
        return SUCCESS_RES;
    }

    public static String createCommonResultJSON(final String key, final Object result, Class clazz, String... includeField) {
        if (null != result) {
            HashMap<String, Object> map = new HashMap<String, Object>() {{
                put(key, result);
            }};
            return createResponseJSON(null, SUCCESS_CODE, map, clazz, includeField);
        }
        return createNullResultJSON();
    }

    private static String createResponseJSON(final String reason, final int code, final Object payload, Class clazz, String... includeField) {
        HashMap<String, Object> jsonObject = getHashMap(reason, code, payload);
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(clazz, includeField);
        return JSON.toJSONString(jsonObject, filter);
    }

    private static HashMap<String, Object> getHashMap(final String reason, final int code, final Object payload) {
        return new HashMap<String, Object>() {{
            if (null != reason) {
                put(ReasonKey, reason);
            }
            put(CodeKey, code);
            if (null != payload) {
                put(PayloadKey, payload);
            }
        }};
    }

    public static String emptyResJSON() {
        return EMPTY_RES;
    }
}
