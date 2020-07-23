package com.zhizu;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;

import java.util.List;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/7/21
 **/
@Data
public class QueuesInfo {
    private List<RabbitMqQueue> rabbitMqQueue;
}
