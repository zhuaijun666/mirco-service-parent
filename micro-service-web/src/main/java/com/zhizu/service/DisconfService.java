package com.zhizu.service;

import com.baidu.disconf.client.common.annotations.DisconfItem;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import com.baidu.disconf.client.usertools.DisconfDataGetter;
import com.baidu.disconf.client.watch.inner.DisconfSysUpdateCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/6/10
 **/
@Service
@DisconfUpdateService(classes={DisconfService.class}, itemKeys = {"disConfStr"})
@Slf4j
public class DisconfService implements IDisconfUpdate {
    private String disConfStr = "";
    private String disNotConfStr = "123";

    @DisconfItem(key = "disNotConfStr")
    public String getDisNotConfStr() {
        return disNotConfStr;
    }

    public void setDisNotConfStr(String disNotConfStr) {
        this.disNotConfStr = disNotConfStr;
    }



    @DisconfItem(key = "disConfStr")
    public String getDisConfStr() {
        return disConfStr;
    }

    @Override
    public void reload() throws Exception {
log.info("reload");
    }
}
