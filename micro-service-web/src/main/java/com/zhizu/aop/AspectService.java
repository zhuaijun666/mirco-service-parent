package com.zhizu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * @Author zhuaijun
 * @Description //TODO
 * @Date 2020/7/3
 **/
@Aspect
@Service
public class AspectService {
    @Pointcut("execution(public * com.zhizu.service.*.*(..))")
    public void LogAspect(){}

    @Before("LogAspect()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("doBefore");
    }
}
