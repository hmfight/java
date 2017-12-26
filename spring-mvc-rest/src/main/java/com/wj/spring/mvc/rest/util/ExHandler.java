package com.wj.spring.mvc.rest.util;

/**
 * @author : wangjia
 * contact : wangjia_yql@qq.com
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wangjia
 * 统一异常处理
 */
@ControllerAdvice(annotations = org.springframework.stereotype.Controller.class)
public class ExHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    private String exceptionHandler(Exception e) {
        String result;
        if (e instanceof EgException) {
            EgException errorException = (EgException) e;
            if (errorException.getReturnCode().getCode().equals(EnumCommonErrorCode.SERVER_ERROR.getCode())) {
                LOGGER.error("error:", e);
            }
            result = EgWebUtils.createResultJson(errorException);
        } else if (e instanceof MissingServletRequestParameterException) {
            LOGGER.warn("missed params :{}", e.getMessage());
            result = EgWebUtils.createResultJsonByEnum(EnumCommonErrorCode.PARAM_ERROR, "missed");
        } else if (e instanceof TypeMismatchException) {
            LOGGER.warn("param type mismatch :{}", e.getMessage());
            result = EgWebUtils.createResultJsonByEnum(EnumCommonErrorCode.PARAM_ERROR, "type");
        } else {
            LOGGER.error("exception", e);
            result = EgWebUtils.createServerErrorJson();
        }
        return result;
    }
}

