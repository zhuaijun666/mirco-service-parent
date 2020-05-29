package com.zhizu;


/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/5/28
 **/

import com.zhizu.dao.mapper.AppAuthMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
public class Test {
    @Autowired
    private AppAuthMapper appAuthMapper;

    @org.junit.Test
    public void Test() {
        appAuthMapper.selectAll();
    }
}
