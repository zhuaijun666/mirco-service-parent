package com.zhizu.disruptor;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/6/17
 **/
public abstract class ValueWrapper<T> {

    private T value;

    public ValueWrapper() {}

    public ValueWrapper(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
