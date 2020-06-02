package com.zhizu;

import com.dianping.cat.Cat;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.smartcardio.CardTerminal;
import java.io.IOException;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/5/20
 **/
@EnableDubbo(scanBasePackages = "com.zhizu")
@SpringBootApplication
@Slf4j
public class WebApplication {

    public static void main(String[] args) throws IOException {
        try {
            Class.forName("org.apache.dubbo.rpc.RpcContext");
        } catch (Exception e) {
        }
        Cat.logMetricForCount("metric.key");
        SpringApplication.run(WebApplication.class, args);
        Cat.getManager().setTraceMode(true);
        log.info("web启动完成");
    }
}
