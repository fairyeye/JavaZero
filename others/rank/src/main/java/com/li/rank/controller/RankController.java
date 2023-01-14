package com.li.rank.controller;

import com.alibaba.fastjson.JSONObject;
import com.li.rank.component.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class RankController {

    @Resource
    private WebSocket webSocket;

    @GetMapping(value = "/{uuid}/sign")
    public String sign(@RequestParam(value = "name") String name,
                       @RequestParam(value = "organ") String organ,
                       @PathVariable String uuid) {
        if (webSocket.getSignNames(uuid).contains(name)) {
            return "不可以重复签到！";
        }
        JSONObject message = new JSONObject();
        message.put("organ", organ);
        webSocket.sendAllMessage(message.toJSONString());
        webSocket.pushSignNames(uuid, name);
        return name + "签到成功";
    }

}
