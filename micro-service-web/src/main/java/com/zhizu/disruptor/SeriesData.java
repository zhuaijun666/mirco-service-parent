package com.zhizu.disruptor;

import lombok.Data;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/6/17
 **/
@Data
public class SeriesData {

    private String deviceInfoStr;

    public SeriesData(String deviceInfoStr) {
        this.deviceInfoStr = deviceInfoStr;
    }

}
