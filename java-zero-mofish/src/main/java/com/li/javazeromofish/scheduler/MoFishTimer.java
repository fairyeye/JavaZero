package com.li.javazeromofish.scheduler;

import com.li.javazeromofish.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/9/2 17:42
 */
@Component
public class MoFishTimer {

//    private static final Logger logger = LoggerFactory.getLogger(MoFishTimer.class);
//
//    @Autowired
//    private MessageService messageService;
//
//    @Scheduled(cron = "0 30 15 * * ? ")
//    public void sendMessageAm() {
//        messageService.sendMessage();
//    }
//
//    @Scheduled(fixedRate = 10000)
//    public void sendMessagePm() {
//        logger.info(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
//    }
}
