package com.li.javazeromofish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableDiscoveryClient
@EnableScheduling
@SpringBootApplication
public class JavaZeroMofishApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaZeroMofishApplication.class, args);
    }

}
