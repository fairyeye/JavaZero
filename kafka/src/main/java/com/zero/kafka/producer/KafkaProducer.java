package com.zero.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

/**
 * @description kafka消费者
 *
 * @author baiye 2021/03/29 10:03 下午
 */
@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    // 发送消息
    @GetMapping("/normal/{message}")
    public void sendMessage(@PathVariable("message") String message) {

        logger.info("=================================>kafka message is：" + message);

//        kafkaTemplate.send("test", message);

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test", message);
        future.addCallback(success -> this.sendSuccessHandler("test", message),
                fail -> sendFailHandler(message, "test"));
    }

    private void sendSuccessHandler(String topic, String message) {
        logger.error(MessageFormat.format("send message {message} to topic {topic} success.", message, topic));
    }

    private void sendFailHandler(String topic, String message) {
        logger.error(MessageFormat.format("send message {message} to topic {topic} fail.", message, topic));
    }
}