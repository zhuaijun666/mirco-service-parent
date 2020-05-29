package com.zhizu.controller;

import com.zhizu.controller.base.dto.JsonResult;
import com.zhizu.dao.model.AppAuth;
import com.zhizu.service.AppAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/5/29
 **/
@RestController
@RequestMapping("hello")
public class HelloController {
    @Autowired
    private AppAuthService appAuthService;

    @RequestMapping("test")
    public JsonResult hello() {
        JsonResult result = new JsonResult();
        AppAuth appAuth = appAuthService.findById(1);


        return JsonResult.success(appAuth);
    }

    @RequestMapping("exp")
    public JsonResult helloExp() {
        JsonResult result = new JsonResult();
        if (1==1)
        throw new RuntimeException("test");

        return JsonResult.success(new AppAuth());
    }
}
