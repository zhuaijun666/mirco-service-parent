package com.zhizu.controller.base.dto;



import com.zhizu.common.business.BussinessException;
import com.zhizu.common.enums.ErrorMessage;

import java.io.Serializable;

/**
 *
 */
public class JsonResult implements Serializable {

    private static final long serialVersionUID = 4149237486683786540L;
    private String code;
    private String msg;
    private Object data;

    public JsonResult() {
    }

    public JsonResult(ErrorMessage message, Object data) {
        this.code = message.getCode();
        this.msg = message.getMsg();
        this.data = data;
    }

    public JsonResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult success() {
        return success(null);
    }

    public static JsonResult success(Object data) {
        return new JsonResult(ErrorMessage.SUCCESS, data);
    }

    public static JsonResult error(String code, String msg, Object data) {
        return new JsonResult(code, msg, data);
    }

    public static JsonResult error() {
        return error(ErrorMessage.SYSTEM_ERROR);
    }

    public static JsonResult error(ErrorMessage message) {
        return error(message, null);
    }

    public static JsonResult error(ErrorMessage message, Object data) {
        return new JsonResult(message.getCode(), message.getMsg(), data);
    }

    public static JsonResult error(BussinessException be) {
        return new JsonResult(be.getCode(), be.getMsg(), null);
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
