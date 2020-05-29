package com.zhizu;

import com.zhizu.rpc.api.TestApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/5/26
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class JunitTest {
    @Autowired
    private TestApi testApi;

    @Test
    public void test() {
        testApi.test(null);

    }
}
