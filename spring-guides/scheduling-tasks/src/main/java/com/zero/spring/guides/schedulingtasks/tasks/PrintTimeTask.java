package com.zero.spring.guides.schedulingtasks.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author baiye
 * @date 2021/04/08 10:35 下午
 */
@Component
public class PrintTimeTask {

    private static final Logger logger = LoggerFactory.getLogger(PrintTimeTask.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 1000)
    public void run() {
        logger.info(sdf.format(new Date()));
    }

}
