package com.li.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class CaptchaController {
    @GetMapping("/code/image")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //创建一个验证码
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        //放到session中
        // 为什么要重构？重构的快捷键是啥？
        String captchaCode = circleCaptcha.getCode();
        log.info("生成的验证码为：{}", captchaCode);
        request.getSession().setAttribute("LOGIN_CAPTCHA_CODE", captchaCode);
        ImageIO.write(circleCaptcha.getImage(), "JPEG", response.getOutputStream());
    }
}
