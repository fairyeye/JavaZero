package com.li.javazeromofish.controller;

import com.li.javazeromofish.service.MessageService;
import com.li.javazeromofish.utils.WeixinCheckoutUtil;
import com.li.javazeromofish.utils.XMLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/9/14 18:00
 */
@RestController
public class RestDemoController {

    private static final Logger logger = LoggerFactory.getLogger(RestDemoController.class);

    @Autowired
    private MessageService messageService;

    /**
     * 微信公众号签名认证接口
     *
     * @throws
     * @Title: test
     * @Description: TODO
     * @param: @param signature
     * @param: @param timestamp
     * @param: @param nonce
     * @param: @param echostr
     * @param: @return
     * @return: String
     */
    @GetMapping("/wx")
    public String wx(String signature, String timestamp, String nonce, String echostr) {

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (signature != null && WeixinCheckoutUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }

        return null;
    }

    @PostMapping("/wx")
    public String receiveMessage(HttpServletRequest request) {
        Map<String, String> map = XMLUtils.xmlToMap(request);
        System.out.println(map);
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        map.put("ToUserName", fromUserName);
        map.put("FromUserName", toUserName);
        String message = messageService.getMessage();
        System.out.println(message);
        map.put("Content", message);
        System.out.println(map);
        return XMLUtils.xmlFormat(map, true);
    }
}
