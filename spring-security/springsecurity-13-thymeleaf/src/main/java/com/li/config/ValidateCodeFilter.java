package com.li.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        log.info("请求的URI为：{}", requestURI);
        if (!requestURI.equals("/login/doLogin")) {
            doFilter(request, response, filterChain);
        } else {
            validateCode(request, response, filterChain);
        }
    }

    private void validateCode(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String enterCaptchaCode = request.getParameter("code");
        HttpSession session = request.getSession();
        String captchaCodeInSession = (String) session.getAttribute("LOGIN_CAPTCHA_CODE");
        log.info("用户输入的验证码为：{},session中的验证码为：{}", enterCaptchaCode, captchaCodeInSession);
        //移除错误信息
        session.removeAttribute("captchaCodeErrorMsg");
        if (!StringUtils.hasText(captchaCodeInSession)) {
            session.removeAttribute("LOGIN_CAPTCHA_CODE");
        }
        if (!StringUtils.hasText(enterCaptchaCode) || !StringUtils.hasText(captchaCodeInSession) || !enterCaptchaCode.equalsIgnoreCase(captchaCodeInSession)) {
            //说明验证码不正确，返回登陆页面
            session.setAttribute("captchaCodeErrorMsg", "验证码不正确");
//重定向
            response.sendRedirect("/login/toLogin");
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
