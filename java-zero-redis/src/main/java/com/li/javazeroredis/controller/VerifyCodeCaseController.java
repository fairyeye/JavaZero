package com.li.javazeroredis.controller;

import com.li.javazeroredis.utils.VerifyCodeUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2022/1/7 14:17
 */
@RestController
@RequestMapping(value = "/case")
public class VerifyCodeCaseController {

    private StringRedisTemplate redisTemplate;

    @GetMapping(value = "/get-verify-code")
    public String getVerifyCode(String phoneNumber) {
        String verifyCode = VerifyCodeUtil.initVerifyCode();

        String verifyCodeKey = "verify:" + phoneNumber + "";
        String verifyCountKey = "verify:" + phoneNumber + ":count";

        return verifyCode;
    }
}
