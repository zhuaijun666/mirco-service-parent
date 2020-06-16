package com.zhizu.controller;

import com.baidu.disconf.client.usertools.DisconfDataGetter;
import com.zhizu.controller.base.dto.JsonResult;
import com.zhizu.controller.bean.AlertMailInfo;
import com.zhizu.dao.model.AppAuth;
import com.zhizu.service.AppAuthService;
import com.zhizu.service.DisconfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/5/29
 **/
@RestController
@RequestMapping("hello")
@Slf4j
public class HelloController {
    @Autowired
    private AppAuthService appAuthService;

    @Autowired
    private DisconfService disconfService;



    @RequestMapping("disConf")
    public JsonResult disConf() {
        JsonResult result = new JsonResult();

        log.info(disconfService.getDisConfStr());
        log.info((String) DisconfDataGetter.getByItem("disNotConfStr"));

        return JsonResult.success(disconfService.getDisConfStr());
    }

    @RequestMapping("test")
    public JsonResult hello(@RequestParam String jsonm) {
        JsonResult result = new JsonResult();
        AppAuth appAuth = appAuthService.findById(1);

        log.info("hello invoke");

        Exception exception =new RuntimeException("test");
        log.error("error", exception);

        return JsonResult.success(appAuth);
    }

    @RequestMapping("sms")
    public JsonResult sms() {
        JsonResult result = new JsonResult();


        return JsonResult.success("");
    }

    @RequestMapping("mail")
    public JsonResult mail(AlertMailInfo alertMailInfo) {
        JsonResult result = new JsonResult();


        return JsonResult.success("");
    }

    @RequestMapping("exp")
    public JsonResult helloExp() {
        JsonResult result = new JsonResult();
        if (1==1)
        throw new RuntimeException("test");

        return JsonResult.success(new AppAuth());
    }
}
