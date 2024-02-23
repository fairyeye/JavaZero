package com.li.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 开启WebSecurity模式
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 注入登陆成功的处理器
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    // 注入登陆失败的处理器
    private final CustomAuthenticationSuccessHandle customAuthenticationSuccessHandle;

    // 注入没有权限的处理器
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    //  注入退出成功的处理器
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    public SecurityConfig(CustomAuthenticationFailureHandler customAuthenticationFailureHandler, CustomAuthenticationSuccessHandle customAuthenticationSuccessHandle, CustomAccessDeniedHandler customAccessDeniedHandler, CustomLogoutSuccessHandler customLogoutSuccessHandler) {
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
        this.customAuthenticationSuccessHandle = customAuthenticationSuccessHandle;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);

        // 放行请求 无需认证
        http.authorizeRequests().antMatchers("/", "/index").permitAll();
        // 拦截请求 需要认证后才能访问
        http.authorizeRequests().anyRequest().authenticated();
        // 开启登录
        http.formLogin().successHandler(customAuthenticationSuccessHandle).permitAll();
        http.logout().logoutSuccessHandler(customLogoutSuccessHandler);
    }

    /*
     * 从 Spring5 开始，强制要求密码要加密
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        //使用加密算法对密码进行加密
        return new BCryptPasswordEncoder();
    }
}