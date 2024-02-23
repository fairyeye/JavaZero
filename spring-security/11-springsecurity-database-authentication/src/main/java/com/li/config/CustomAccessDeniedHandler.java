package com.li.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.vo.HttpResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 无权限的处理器
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    //声明一个把对象转成JSON的对象
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException{
        //设置响应编码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        //返回JSON出去
        HttpResult result=new HttpResult(-1,"您没有权限访问");
        //把result转成JSON
        String json = objectMapper.writeValueAsString(result);
        //响应出去
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
    }
}
