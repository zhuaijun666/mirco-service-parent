package com.zhizu.rpc.api;

import com.zhizu.rpc.api.dto.TestBean;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/5/19
 **/
public interface TestApi {
    /**
     *
     * @param testBean
     */
    TestBean invoke(TestBean testBean);
}
