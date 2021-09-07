package com.li.javazeromofish.scheduler;

import com.li.javazeromofish.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/9/2 17:42
 */
@Component
public class MoFishTimer {

    @Autowired
    private MessageService messageService;

    @Scheduled(cron = "0 30 15 * * ? ")
    public void sendMessageAm() {
        messageService.sendMessage();
        System.out.println(new Date());
    }

    @Scheduled(fixedRate = 10000)
    public void sendMessagePm() {
        System.out.println(new Date());
    }
}
