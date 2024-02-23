package com.li.debezium.demos.web;

import io.debezium.connector.mysql.MySqlConnector;
import io.debezium.relational.history.FileDatabaseHistory;
import lombok.Data;
import org.apache.kafka.connect.storage.FileOffsetBackingStore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.File;
import java.io.IOException;

/**
 * @className: MysqlConfig
 * @author: nyp
 * @description:
 * @date: 2023/8/7 13:53
 * @version: 1.0
 */
@Configuration
@ConfigurationProperties(prefix ="debezium.datasource")
@Data
public class MysqlBinlogConfig {
 
    private String hostname;
    private String port;
    private String user;
    private String password;
    private String tableWhitelist;
    private String storageFile;
    private String historyFile;
    private Long flushInterval;
    private String serverId;
    private String serverName;
 
    @Bean
    public io.debezium.config.Configuration MysqlConnector() throws Exception {
        checkFile();
        io.debezium.config.Configuration configuration = io.debezium.config.Configuration.create()
                .with("name", "mysql_connector")
                .with("connector.class", MySqlConnector.class)
                // .with("offset.storage", KafkaOffsetBackingStore.class)
                // .with("offset.storage", FileOffsetBackingStore.class)
                .with("offset.storage.file.filename", storageFile)
                .with("offset.flush.interval.ms", flushInterval)
                .with("database.history", FileDatabaseHistory.class.getName())
                .with("database.history.file.filename", historyFile)
                .with("snapshot.mode", "initial")
                .with("database.server.id", serverId)
                .with("database.server.name", serverName)
                .with("database.hostname", hostname)
                // .with("database.dbname", "db")
                .with("database.port", port)
                .with("database.user", user)
                .with("database.password", password)
                // .with("converter.schemas.enable", "false")
               // .with("database.whitelist", "db")
                .with("table.whitelist", tableWhitelist)
                .build();
        return configuration;
    }
 
    private void checkFile() throws IOException {
        String dir = storageFile.substring(0, storageFile.lastIndexOf("/"));
        File dirFile = new File(dir);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        File file = new File(storageFile);
        if(!file.exists()){
            file.createNewFile();
        }
    }
}