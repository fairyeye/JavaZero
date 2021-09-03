package com.li.javazeromofish.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/9/2 17:42
 */
@Component
public class MoFishTimer {

    @Scheduled(fixedRate = 10000)
    public void sendMessageAm() {
        System.out.println(new Date());
    }

    @Scheduled(fixedRate = 10000)
    public void sendMessagePm() {
        System.out.println(new Date());
    }
}
