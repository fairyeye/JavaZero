package com.li.multithread.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.li.multithread.service.GrabOrderDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/8/18 14:42
 */
@Service(value = "noLockService")
public class GrabOrderDemoNoLockServiceImpl implements GrabOrderDemoService {

    private static final Logger logger = LoggerFactory.getLogger(GrabOrderDemoNoLockServiceImpl.class);

    @Override
    public void grabOrder(Map<String, String> map, String id) throws InterruptedException {
        Thread.sleep(1000L);
        if ("no".equals(map.get("1"))) {
            logger.info("==========> {} get order!", id);
            map.put("1", id);
            return;
        }
        logger.info("==========> order has been got,id is {},map info is : {}", id, JSONObject.toJSONString(map));

    }
}
