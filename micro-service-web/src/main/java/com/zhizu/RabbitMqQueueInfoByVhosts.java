package com.zhizu;

import lombok.Data;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/7/21
 **/
@Data
public class RabbitMqQueueInfoByVhosts {
    private String name;
    private Integer messages_ready;

}
