package com.wj.spring.mvc.rest.util;


/**
 * @author : wangjia_yql@qq.com
 */
public enum EnumUserErrorCode implements IEgReturnCode {

    NAME_EXIST(101, "name exist"),
    NAME_OR_PW_ERROR(102, "password error");

    private Integer code;
    private String reason;

    EnumUserErrorCode(Integer code, String reason) {
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
