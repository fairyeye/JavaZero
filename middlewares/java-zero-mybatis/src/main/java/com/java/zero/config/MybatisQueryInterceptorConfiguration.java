package com.java.zero.config;

import com.java.zero.interceptor.MybatisInterceptor;
import com.java.zero.interceptor.MybatisQueryInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisQueryInterceptorConfiguration {

//    @Bean
//    MybatisQueryInterceptor mybatisQueryInterceptor() {
//        return new MybatisQueryInterceptor();
//    }

    @Bean
    MybatisInterceptor mybatisInterceptor() {
        return new MybatisInterceptor();
    }
}
