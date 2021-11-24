package com.li.javazeromofish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@EnableDiscoveryClient
@EnableScheduling
@RestController
@SpringBootApplication
public class JavaZeroMofishApplication {

    private static final Logger logger = LoggerFactory.getLogger(JavaZeroMofishApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JavaZeroMofishApplication.class, args);
    }

    @RequestMapping(value = "/fun")
    public String function() {
        logger.info("success");
        return "SUCCESS";
    }
}
