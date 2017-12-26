package com.wj.spring.mvc.rest.util;


/**
 * @author : wangjia_yql@qq.com
 */
public enum EnumCommonErrorCode implements IEgReturnCode {

    SUCCESS(0, "success"),
    SERVER_ERROR(-1, "unkown server error!"),
    PARAM_ERROR(-2, "param error");

    private Integer code;
    private String reason;

    EnumCommonErrorCode(Integer code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getReason() {
        return reason;
    }

    }
