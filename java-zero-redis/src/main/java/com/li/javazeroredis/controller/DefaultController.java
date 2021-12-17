package com.li.javazeroredis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/12/9 21:02
 */
@RestController
public class DefaultController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

    /**
     * 减少库存
     */
    @RequestMapping(value = "/test")
    public String test()  {
        synchronized (this) {
            String count = redisTemplate.opsForValue().get("count");
            int anInt = Integer.parseInt(count);
            if (anInt > 0) {
                LOGGER.info("-----------------------库存扣减：{} -> {}。", anInt, --anInt);
                redisTemplate.opsForValue().set("count", String.valueOf(anInt));
            } else {
                LOGGER.info("-----------------------库存不足！");
            }
        }
        return "OK";
    }
}
