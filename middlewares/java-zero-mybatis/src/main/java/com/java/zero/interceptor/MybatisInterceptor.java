package com.java.zero.interceptor;

import com.alibaba.fastjson2.JSON;
import com.java.zero.entity.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Date;
import java.util.Properties;

@Intercepts(
        @Signature(
                type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}
        )
)
public class MybatisInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("Before MybatisQueryInterceptor······");
        // 1. 获取MappedStatement实例, 并获取当前SQL命令类型
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        String methodName = invocation.getMethod().getName();
        BoundSql boundSql = ms.getBoundSql(parameter);
        System.out.println(boundSql.getSql());
        System.out.println(JSON.toJSONString(parameter));
        if ("update".equals(methodName)) {
            System.out.println("update...");
            if (parameter instanceof BaseEntity) {
                BaseEntity entity = (BaseEntity) parameter;
                entity.setCreateTime(new Date());
                entity.setUpdateTime(new Date());
                parameter = entity;
                System.out.println(JSON.toJSONString(parameter));
            }
        }
        System.out.println("methodName: " + methodName);
        System.out.println("After MybatisQueryInterceptor······");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
