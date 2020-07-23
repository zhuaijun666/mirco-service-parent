package com.zhizu;

import com.zhizu.service.AOPService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/7/3
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})
public class AOPTest {
    @Autowired
    private AOPService aOPService;

    @org.junit.Test
    public void Test() {
        aOPService.test1();
    }
}
