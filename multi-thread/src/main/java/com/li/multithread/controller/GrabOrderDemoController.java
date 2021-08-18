package com.li.multithread.controller;

import com.alibaba.fastjson.JSONObject;
import com.li.multithread.service.GrabOrderDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/8/16 21:00
 */
@RestController("grabOrderDemoController")
@RequestMapping("/demo/grab-order")
public class GrabOrderDemoController {

    @Autowired
//    @Qualifier("noLockService")   // 无锁
    @Qualifier("jvmLockService")    // JVM锁
    private GrabOrderDemoService grabOrderDemoService;

    private static final Logger logger = LoggerFactory.getLogger(GrabOrderDemoController.class);

    private static final Map<String, String> map = new HashMap<>();

    public GrabOrderDemoController() {
        map.put("1", "no");
        logger.info("==========> map info is : {}", JSONObject.toJSONString(map));
    }

    @PostMapping
    public void grabOrder(@RequestParam String id) throws InterruptedException {
        grabOrderDemoService.grabOrder(map, id);
    }

    @GetMapping
    public String queryGrabOrder(@RequestParam(required = false) String id) throws InterruptedException {
        Thread.sleep(1000L);
        logger.info("==========> map info is : {}", JSONObject.toJSONString(map));
        return JSONObject.toJSONString(map);
    }

    @DeleteMapping
    public void initGrabOrder() {
        map.put("1", "no");
        logger.info("==========> map info is : {}", JSONObject.toJSONString(map));
    }
}
