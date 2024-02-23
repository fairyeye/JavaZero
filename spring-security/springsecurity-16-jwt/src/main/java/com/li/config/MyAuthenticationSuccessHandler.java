package com.li.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.utils.JwtUtils;
import com.li.vo.HttpResult;
import com.li.vo.SecurityUser;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 认证成功处理器，当用户登录成功后，会执行此处理器
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    //使用此工具类进行序列化
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        //从认证对象中获取认证用户信息
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String userInfo = objectMapper.writeValueAsString(securityUser.getSysUser());

        List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) securityUser.getAuthorities();
        // 转成字符数组
        List<String> authList = authorities.stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.toList());

        // 创建jwt
        String token = jwtUtils.createToken(userInfo, authList);

        // 写入Redis
        stringRedisTemplate.opsForValue().set("logintoken:" + token, objectMapper.writeValueAsString(authentication), 30, TimeUnit.MINUTES);

        //返回给前端token
        HttpResult httpResult = HttpResult.builder().code(200).msg("OK").data(token).build();
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(httpResult));
        writer.flush();
    }
}
