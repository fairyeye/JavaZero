package com.li.debezium.demos.web;

import com.alibaba.fastjson.JSON;
import io.debezium.config.Configuration;
import io.debezium.data.Envelope;
import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.format.Json;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executor;

/**
 * @projectName: test
 * @package: com.test.config
 * @className: MysqlBinlogListener
 * @author: nyp
 * @description: TODO
 * @date: 2023/8/7 13:56
 * @version: 1.0
 */
@Component
@Slf4j
public class MysqlBinlogListener {
 
    @Resource
    private Executor taskExecutor;
 
    private final List<DebeziumEngine<ChangeEvent<String, String>>> engineList = new ArrayList<>();

    private MysqlBinlogListener(@Qualifier("MysqlConnector") Configuration configuration) {
        log.info("======>MysqlBinlogListener constructor init...");
        this.engineList.add(DebeziumEngine.create(Json.class)
                .using(configuration.asProperties())
                .notifying(record -> receiveChangeEvent(record.value()))
                .build());
    }

    private void receiveChangeEvent(String value) {
        log.info("======>MysqlBinlogListener receiveChangeEvent...");
        if (Objects.nonNull(value)) {
            Map<String, Object> payload = getPayload(value);
            String op = JSON.parseObject(JSON.toJSONString(payload.get("op")), String.class);
            if (!(StringUtils.isBlank(op) || Envelope.Operation.READ.equals(op))) {
                ChangeData changeData = getChangeData(payload);
                log.info("changeData = " + changeData);
                switch (changeData.table) {
                    case "user":
                        log.info("user表发生了变更");
                        break;
                    default:
                        break;
                }
            }
        }
    }
 
    @PostConstruct
    private void start() {
        for (DebeziumEngine<ChangeEvent<String, String>> engine : engineList) {
            log.info("======>MysqlBinlogListener start...");
            taskExecutor.execute(engine);
        }
    }
 
    @PreDestroy
    private void stop() {
        log.info("======>MysqlBinlogListener stop...");
        for (DebeziumEngine<ChangeEvent<String, String>> engine : engineList) {
            if (engine != null) {
                try {
                    engine.close();
                } catch (IOException e) {
                    log.error("IOException", e);
                }
            }
        }
    }
 
 
    public static Map<String, Object> getPayload(String value) {
        Map<String, Object> map = JSON.parseObject(value, Map.class);
        Map<String, Object> payload = JSON.parseObject(JSON.toJSONString(map.get("payload")), Map.class);
        return payload;
    }
 
    public static ChangeData getChangeData(Map<String, Object> payload) {
        Map<String, Object> source = JSON.parseObject(JSON.toJSONString(payload.get("source")), Map.class);
        return ChangeData.builder()
                .op(payload.get("op").toString())
                .table(source.get("table").toString())
                .after(Objects.isNull(payload.get("after")) ? new HashMap<>() : JSON.parseObject(JSON.toJSONString(payload.get("after")), Map.class))
                .source(Objects.isNull(payload.get("source")) ? new HashMap<>() : JSON.parseObject(JSON.toJSONString(payload.get("source")), Map.class))
                .before(Objects.isNull(payload.get("before")) ? new HashMap<>() : JSON.parseObject(JSON.toJSONString(payload.get("before")), Map.class))
                .build();
    }
 
    @Data
    @Builder
    public static class ChangeData {
        /**
         * 更改前数据
         */
        private Map<String, Object> after;
        private Map<String, Object> source;
        /**
         * 更改后数据
         */
        private Map<String, Object> before;
        /**
         * 更改的表名
         */
        private String table;
        /**
         * 操作类型, 枚举 Envelope.Operation
         */
        private String op;
    }
 
}