package com.zhizu.common.business;

import com.zhizu.common.enums.ErrorMessage;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/5/29
 **/
public class BussinessException extends RuntimeException {
    private String code;
    private String msg;

    public BussinessException(ErrorMessage errorMessage) {
        this.code = errorMessage.getCode();
        this.msg = errorMessage.getMsg();
    }

    public BussinessException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BussinessException(String message) {
        super(message);
        this.code = ErrorMessage.SYSTEM_ERROR.getCode();
        this.msg = message;
    }

    public BussinessException(String message, Throwable cause) {
        super(message, cause);
        this.code = ErrorMessage.SYSTEM_ERROR.getCode();
        this.msg = message;
    }

    public BussinessException(Throwable cause) {
        super(cause);
        this.code = ErrorMessage.SYSTEM_ERROR.getCode();
        this.msg = ErrorMessage.SYSTEM_ERROR.getMsg();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
