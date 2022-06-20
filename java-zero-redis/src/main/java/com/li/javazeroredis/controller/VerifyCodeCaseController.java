package com.li.javazeroredis.controller;

import com.li.javazeroredis.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Objects;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2022/1/7 14:17
 */
@RestController
@RequestMapping(value = "/case")
public class VerifyCodeCaseController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping(value = "/get-verify-code")
    public String getVerifyCode(String phoneNumber) throws Exception {
        // 声明 redis key
        String verifyCodeKey = "verify:" + phoneNumber + "";
        String verifyCountKey = "verify:" + phoneNumber + ":count";

        // 获取验证码
        String verifyCode = redisTemplate.opsForValue().get(verifyCodeKey);
        String verifyCountStr = redisTemplate.opsForValue().get(verifyCountKey);
        int verifyCount = 0;
        if (!StringUtils.isEmpty(verifyCountStr)) {
            verifyCount = Integer.parseInt(verifyCountStr);
        }

        if (StringUtils.isEmpty(verifyCode)) {
            // 没有有效的验证码
            if (verifyCount == 3) {
                // 当天获取过三次验证码
                throw new Exception("超过3次!");
            }
            // 不超过3次 验证码次数+1
            verifyCount++;
            // 生成验证码
            verifyCode = VerifyCodeUtil.initVerifyCode();
            // 赋值
            redisTemplate.opsForValue().setIfAbsent(verifyCodeKey, verifyCode, Duration.ofMinutes(1L));
            redisTemplate.opsForValue().set(verifyCountKey, verifyCount + "", Duration.ofDays(1L));
        } else {
            throw new Exception("不要重复获取!");
        }

        return verifyCode;
    }
}
