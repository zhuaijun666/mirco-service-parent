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
        for (int i = 0; i< 100; i++) {
            testApi.invoke(new TestBean());
            log.info("dubbo invoker");
        }

    }
}
