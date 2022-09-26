package com.li.javazeromofish.service;

import org.springframework.stereotype.Service;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/9/2 19:22
 */
@Service
public interface MessageService {

    void sendMessage();

    /**
     * 假期信息
     * @return
     */
    String getMessage();

    String initMessage(String content);

}
