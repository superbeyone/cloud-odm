package com.tdt.cloud.commons.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className OdmConfig
 * @description 自定义配置信息
 * @date 2019-04-01 16:32
 **/
@Component
@ConfigurationProperties(prefix = "tdt.odm")
public class OdmProperties {
    private StorageProperties storage;
    private MongoConnProperties conn;

    private ThreadProperties thread;

    private Map<String, Map<String, String>> satelliteCenterMap = new HashMap<>();

    public StorageProperties getStorage() {
        return storage;
    }

    public void setStorage(StorageProperties storage) {
        this.storage = storage;
    }

    public MongoConnProperties getConn() {
        return conn;
    }

    public void setConn(MongoConnProperties conn) {
        this.conn = conn;
    }

    public ThreadProperties getThread() {
        return thread;
    }

    public void setThread(ThreadProperties thread) {
        this.thread = thread;
    }

    public Map<String, Map<String, String>> getSatelliteCenterMap() {
        return satelliteCenterMap;
    }

    public void setSatelliteCenterMap(Map<String, Map<String, String>> satelliteCenterMap) {
        this.satelliteCenterMap = satelliteCenterMap;
    }
}
