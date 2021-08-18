package com.zero.spring.guides.schedulingtasks.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baiye
 * @date 2021/04/08 10:35 下午
 */
@Component
public class PrintTimeTask {

    private static final Logger logger = LoggerFactory.getLogger(PrintTimeTask.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    RestTemplate restTemplate;

    @Scheduled(fixedRate = 10000)
    public void run() {
        Map<String, String> map = new HashMap<>();
        map.put("encode", "text");
        String hitokoto = restTemplate.getForObject("https://v1.hitokoto.cn", String.class);
        logger.info(sdf.format(new Date()) + "\t" + hitokoto);
    }

}
