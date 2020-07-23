package com.zhizu.disruptor;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/6/17
 **/
public class EventFactory implements com.lmax.disruptor.EventFactory<SeriesDataEvent> {

    public SeriesDataEvent newInstance() {
        return new SeriesDataEvent();
    }
}
