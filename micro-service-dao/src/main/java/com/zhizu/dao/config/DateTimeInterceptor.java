package com.zhizu.dao.config;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Properties;

/**
 * 自动注入数据的创建时间及更新时间
 * Created by zhengfeng on 2016-07-20 16:55.
 */

@Component
@Intercepts(@Signature(method="update", type = Executor.class, args={MappedStatement.class, Object.class}))
public class DateTimeInterceptor implements Interceptor {

    private static String[] createdNames = {"created_at", "createdAt", "create_time", "createTime"};
    private static String[] updatedNames = {"updated_at", "updatedAt", "update_time", "updateTime"};

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取MappedStatement对象
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // 参数获取
        Object params = invocation.getArgs()[1];
        // SQL命令类型获取
        SqlCommandType sqlCommandType = (SqlCommandType) PropertyUtils.getProperty(mappedStatement, "sqlCommandType");

        // 创建时间
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            setTime(params, createdNames);
        }

        // 更新时间
        setTime(params, updatedNames);

        // 执行目标方法
        return invocation.proceed();
    }

    private void setTime(Object params, String[] timeNames) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        if(params == null) return;

        Date now = new Date();
        for (String name : timeNames) {
            PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(params, name);
            if (propertyDescriptor != null) {
                PropertyUtils.setProperty(params, name, now);
            }
        }

    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
