package com.zhizu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/5/20
 **/
@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
@Slf4j
public class ConsumerApplication {

    public static void main(String[] args) {
        try {
            Class.forName("org.apache.dubbo.rpc.RpcContext");
        } catch (Exception e) {
        }
        SpringApplication.run(ConsumerApplication.class, args);

        log.info("consummer success");
    }
}
