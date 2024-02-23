package com.li.config;

import com.li.filter.JwtCheckFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Resource
    private JwtCheckFilter jwtCheckFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 增加过滤器
        http.addFilterBefore(jwtCheckFilter, UsernamePasswordAuthenticationFilter.class);
        // 设置登录成功处理器
        http.formLogin().successHandler(myAuthenticationSuccessHandler).permitAll();
        // 设置访问权限
        http.authorizeRequests()
                .mvcMatchers("/student/**").hasAnyAuthority("student:query", "student:update")
                .anyRequest().authenticated(); //任何请求均需要认证（登录成功）才能访问
        //禁用session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 设置注销成功处理器
        http.logout().logoutSuccessHandler(myLogoutSuccessHandler);
        http.csrf().disable();
    }

    /**
     * 从 Spring5 开始，强制要求密码要加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
