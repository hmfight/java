package com.wj.spring.mvc.rest.util;

/**
 * @author : wangjia
 * @time : 2017/4/14 11:04
 */
public class EgException extends RuntimeException {

    private IEgReturnCode returnCode;
    private String extraMessage;

    public EgException(IEgReturnCode returnCode) {
        super("error code = " + returnCode.getCode() + ";error describe:" + returnCode.getReason());
        this.returnCode = returnCode;
    }

    public EgException(IEgReturnCode errorCode, String extraErrorMessage) {
        super("error code = " + errorCode.getCode() + ";error describe:" + errorCode.getReason() + "extra:" + extraErrorMessage);
        this.returnCode = errorCode;
        this.extraMessage = extraErrorMessage;
    }

    public EgException(IEgReturnCode errorCode, Object extraErrorMessage) {
        super("error code = " + errorCode.getCode() + ";error describe:" + errorCode.getReason() + "extra:" + extraErrorMessage.toString());
        this.returnCode = errorCode;
        this.extraMessage = extraErrorMessage.toString();
    }

    public IEgReturnCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(IEgReturnCode returnCode) {
        this.returnCode = returnCode;
    }

    public void setExtraMessage(String extraMessage) {
        this.extraMessage = extraMessage;
    }

    public String getExtraMessage() {
        return extraMessage;
    }

}
