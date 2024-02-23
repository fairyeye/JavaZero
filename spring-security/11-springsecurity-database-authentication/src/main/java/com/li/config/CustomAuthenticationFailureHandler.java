package com.li.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.vo.HttpResult;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登陆失败的处理器
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Resource
    private ObjectMapper objectMapper;


    /**
     * @param request 当前的请求对象
     * @param response 当前的响应对象
     * @param exception 失败的原因的异常
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException{
        System.err.println("登陆失败");
        //设置响应编码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        //返回JSON出去
        HttpResult result=new HttpResult(-1,"登陆失败");
        if(exception instanceof BadCredentialsException){
            result.setData("密码不正确");
        }else if(exception instanceof DisabledException){
            result.setData("账号被禁用");
        }else if(exception instanceof UsernameNotFoundException){
            result.setData("用户名不存在");
        }else if(exception instanceof CredentialsExpiredException){
            result.setData("密码已过期");
        }else if(exception instanceof AccountExpiredException){
            result.setData("账号已过期");
        }else if(exception instanceof LockedException){
            result.setData("账号被锁定");
        }else{
            System.out.println(exception);
            result.setData("未知异常");
        }
        //把result转成JSON
        String json = objectMapper.writeValueAsString(result);
        //响应出去
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
    }

}
