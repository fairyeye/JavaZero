package com.li.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.vo.HttpResult;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 退出成功处理器，用户退出成功后，执行此处理器
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    //使用此工具类的对象进行序列化操作
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //从请求头中获取Authorization信息
        String authorization = request.getHeader("Authorization");
        //如果授权信息为空，返回前端
        if (null == authorization) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            HttpResult httpResult = HttpResult.builder().code(-1).msg("token不能为空").build();
            PrintWriter writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(httpResult));
            writer.flush();
            return;
        }
        //如果Authorization信息不为空，去掉头部的Bearer字符串
        String token = authorization.replace("Bearer ", "");

        //redis中删除token，这是关键点
        stringRedisTemplate.delete("logintoken:" + token);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        HttpResult httpResult = HttpResult.builder().code(200).msg("退出成功").build();
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(httpResult));
        writer.flush();
    }
}
