package com.li.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.entity.SysUser;
import com.li.utils.JwtUtils;
import com.li.vo.HttpResult;
import com.li.vo.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 定义一次性请求过滤器
 */
@Component
@Slf4j
public class JwtCheckFilter extends OncePerRequestFilter {
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求uri
        String requestURI = request.getRequestURI();
        // 如果是登录页面，放行
        if (requestURI.equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        //获取请求头中的Authorization
        String authorization = request.getHeader("Authorization");
        //如果Authorization为空，那么不允许用户访问，直接返回
        if (!StringUtils.hasText(authorization)) {
            printFront(response, "没有登录！");
            return;
        }
        //Authorization 去掉头部的Bearer 信息，获取token值
        String jwtToken = authorization.replace("Bearer ", "");
        //验签
        boolean verifyTokenResult = jwtUtils.verifyToken(jwtToken);
        //验签不成功
        if (!verifyTokenResult) {
            printFront(response, "jwtToken 已过期");
            return;
        }

        // 从Redis获取token并校验
        String tokenInRedis = stringRedisTemplate.opsForValue().get("logintoken:" + jwtToken);
        if (!StringUtils.hasText(tokenInRedis)) {
            printFront(response, "用户已退出，请重新登录");
            return;
        }

        //从payload中获取userInfo
        String userInfo = jwtUtils.getUserInfo(jwtToken);
        //从payload中获取授权列表
        List<String> userAuth = jwtUtils.getUserAuth(jwtToken);
        //创建登录用户
        SysUser sysUser = objectMapper.readValue(userInfo, SysUser.class);
        SecurityUser securityUser = new SecurityUser(sysUser);
        //设置权限
        List<SimpleGrantedAuthority> authList = userAuth.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        securityUser.setSimpleGrantedAuthorities(authList);


        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToke = new UsernamePasswordAuthenticationToken(securityUser
                , null, authList);
        //通过安全上下文设置认证信息
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToke);
        //继续访问相应的rul等
        filterChain.doFilter(request, response);

    }

    private void printFront(HttpServletResponse response, String message) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(-1);
        httpResult.setMsg(message);

        writer.print(objectMapper.writeValueAsString(httpResult));
        writer.flush();
    }
}

