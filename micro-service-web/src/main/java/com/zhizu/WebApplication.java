package com.zhizu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/5/20
 **/
@SpringBootApplication
@Slf4j
public class WebApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(WebApplication.class, args);
        log.info("web启动完成");
    }
}
