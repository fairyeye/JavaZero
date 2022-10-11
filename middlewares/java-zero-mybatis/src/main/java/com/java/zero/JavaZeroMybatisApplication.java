package com.java.zero;

import com.java.zero.mapper.SchoolMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class JavaZeroMybatisApplication {

    @Resource
    private SchoolMapper schoolMapper;

    public static void main(String[] args) {
        SpringApplication.run(JavaZeroMybatisApplication.class, args);
    }



}
