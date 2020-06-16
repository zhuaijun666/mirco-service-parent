package com.zhizu.controller.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/6/2
 **/
@Data
public class AlertMailInfo implements Serializable {

    private static final long serialVersionUID = 2874103275044580327L;
    private String type;

    private String key;
    private String re;

    private String to;
    private String value;
}
