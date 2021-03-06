package com.zhizu.consumer;

import com.zhizu.rpc.api.TestApi;
import com.zhizu.rpc.api.dto.TestBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/5/20
 **/
@Service
@Slf4j
public class TestApiConsumer  {
    @Autowired
    private TestApi testApi;

    @PostConstruct
    public void testApi() {

        while(true) {
            testApi.invoke(new TestBean());
            log.info("dubbo invoker");

            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
