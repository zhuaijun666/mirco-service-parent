package com.zhizu;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/7/21
 **/
@Data
public class RabbitMqQueue {
    private String name;
    private Integer messages_ready;
}
