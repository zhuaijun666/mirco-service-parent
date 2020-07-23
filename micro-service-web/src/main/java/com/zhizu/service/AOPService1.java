package com.zhizu.service;

import com.dianping.cat.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.resources.cldr.ta.CalendarData_ta_IN;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/7/3
 **/
@Service
public class AOPService1 {
    @Autowired
    private AOPService1 aOPService1;

    public void test1() {
        System.out.println("test1");
        test2();
        aOPService1.test2();
    }

    public void test2() {
        System.out.println("test2");
    }
}
