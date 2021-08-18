package com.zero.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * @description kafka消费者
 *
 * @author baiye 2021/03/29 10:03 下午
 */
@Component
public class KafkaConsumer {

    private static final String KAFKA_TOPIC = "test";
    private static final String KAFKA_GROUP = "kafka";

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = KafkaConsumer.KAFKA_TOPIC, groupId = KafkaConsumer.KAFKA_GROUP)
    public void consume(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (Objects.nonNull(message)) {
            Object msg = message.get();
            logger.info("consume consume： Topic:" + topic + "\tMessage:" + msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaConsumer.KAFKA_TOPIC, groupId = KafkaConsumer.KAFKA_GROUP)
    public void consumeTwo(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (Objects.nonNull(message)) {
            Object msg = message.get();
            logger.info("consumeTwo consume： Topic:" + topic + "\tMessage:" + msg);
            ack.acknowledge();
        }
    }

}
