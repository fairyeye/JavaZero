package com.li.rank;

import com.alibaba.fastjson.JSONObject;
import com.li.rank.component.WebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@SpringBootApplication
public class RankApplication {

    public static void main(String[] args) {
        SpringApplication.run(RankApplication.class, args);
    }

}
