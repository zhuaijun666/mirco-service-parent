package com.zhizu;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.zhizu.dao.model.AppAuth;
import com.zhizu.service.impl.AppAuthServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/5/28
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    private AppAuthServiceImpl deviceServiceImpl;

    @org.junit.Test
    public void Test() {
        AppAuth appAuth = new AppAuth();
        appAuth.setAppKey("test");
        appAuth.setAppSecret("test");
        appAuth.setUserId("test");
        appAuth.setEnabled(new Byte("1"));
        appAuth.setDateCreated(DateUtil.date());
        appAuth.setDateCreated(DateUtil.date());

        deviceServiceImpl.insert(appAuth);
        appAuth.setId(null);
        deviceServiceImpl.insert(appAuth);
        appAuth.setId(null);
        deviceServiceImpl.insert(appAuth);
    }

    @org.junit.Test
    public void  searchTest() {
        PageHelper.startPage(0, 1);
        List<AppAuth> appAuthList = deviceServiceImpl.selectAll();

    }


}
