package com.li.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.vo.HttpResult;
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
 * 退出成功的处理器
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    //声明一个把对象转成JSON的对象
    @Resource
    private ObjectMapper objectMapper;

    /**
     *
     * @param request
     * @param response
     * @param authentication 当前退出的用户对象
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("退出成功");
        //设置响应编码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        //返回JSON出去
        HttpResult result=new HttpResult(200,"退出成功");
        //把result转成JSON
        String json = objectMapper.writeValueAsString(result);
        //响应出去
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
    }
}
