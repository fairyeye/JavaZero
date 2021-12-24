package com.li.javazeromofish;

import com.dtflys.forest.springboot.annotation.ForestScan;
import com.li.javazeromofish.client.HitokotoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@EnableDiscoveryClient
@EnableScheduling
@RestController
@ForestScan(value = "com.li.javazeromofish.client")
@SpringBootApplication
public class JavaZeroMofishApplication {

    @Autowired
    private HitokotoClient hitokotoClient;

    private static final Logger logger = LoggerFactory.getLogger(JavaZeroMofishApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JavaZeroMofishApplication.class, args);
    }

    @RequestMapping(value = "/fun")
    public String function() {
        String i = hitokotoClient.getString('i');
        logger.info(i);
        return "SUCCESS";
    }
}
