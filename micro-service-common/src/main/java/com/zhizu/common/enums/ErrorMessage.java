package com.zhizu.common.enums;

/**
 * Created by zino on 28/11/2016.
 */
public enum ErrorMessage {
    SUCCESS("0", "success"),
    SYSTEM_ERROR("1", "系统错误"),
    FORM_ERROR("2", "表单错误"),
    NO_PERMISSION("3", "无权限"),

    UN_LOGIN("1000", "未登陆"),
    LOGIN_ERROR("1001", "登陆失败"),
    LOGIN_PASSWORD_ERROR("1002", "用户名密码不正确"),

    BUSINESS_EXIST("2000", "该业务已经存在"),
    EVENT_EXIST("2001", "该事件已经存在"),
    ATTRIBUTE_EXIST("2002", "该属性已经存在"),
    ATOMRULE_EXIST("2003", "该规则已经存在"),
    GROUPRULE_EXIST("2004", "该规则组已经存在") ,
    ACCESS_EVENT_EXIST("2005", "该风控业务规则已经存在") ,
    GROOVY_INCORRECT("2006", "脚本不正确请重新输入") ;
    ErrorMessage(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

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
