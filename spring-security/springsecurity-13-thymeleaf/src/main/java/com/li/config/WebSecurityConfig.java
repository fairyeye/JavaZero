package com.li.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private ValidateCodeFilter validateCodeFilter;

    /**
     * Security的http请求配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //设置登陆方式
        http.formLogin()//使用用户名和密码的登陆方式
                .usernameParameter("uname") //页面表单的用户名的name
                .passwordParameter("pwd")//页面表单的密码的name
                .loginPage("/login/toLogin") //自己定义登陆页面的地址
                .loginProcessingUrl("/login/doLogin")//配置登陆的url
                .successForwardUrl("/index/toIndex") //登陆成功跳转的页面
                .failureForwardUrl("/login/toLogin")//登陆失败跳转的页面
                .permitAll();
        //配置退出方式
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login/toLogin")
                .permitAll();
        //配置路径拦截 的url的匹配规则
        http.authorizeRequests().antMatchers("/code/image").permitAll()
                //任何路径要求必须认证之后才能访问
                .anyRequest().authenticated();
        // 禁用csrf跨站请求，注意不要写错了
        http.csrf().disable();
        // 配置登录之前添加一个验证码的过滤器
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }


    /**
     * 资源服务匹配放行【静态资源文件】
     *
     * @param web
     * @throws Exception
     */
    // @Override
    // public void configure(WebSecurity web) throws Exception {
    //     web.ignoring().mvcMatchers("/resources/**");
    // }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
