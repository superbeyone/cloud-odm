package com.tdt.cloud.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoIterable;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className MongoUtil
 * @description
 * @date 2019-06-13 14:38
 **/
public class MongoUtil {
    /**
     * 创建 MongoClient
     *
     * @param ip
     * @param port
     * @param username
     * @param password
     * @return
     */
    public static MongoClient getMongoClient(String ip, int port, String username, String password) {
        StringBuilder mongoURI = new StringBuilder();
        mongoURI.append("mongodb://");
        if (StringUtils.isNotBlank(username) || StringUtils.isNotBlank(password)) {
            mongoURI.append(username).append(":").append(password).append("@").append(ip).append(":").append(port).append("/admin?authSource=admin&authMechanism=SCRAM-SHA-1");
        } else {
            mongoURI.append(ip).append(":").append(port).append("/admin&authMechanism=SCRAM-SHA-1");
        }
        MongoClientOptions.Builder build = new MongoClientOptions.Builder();
        build.connectTimeout(500);
        build.serverSelectionTimeout(500);
        build.maxConnectionIdleTime(120000);
        build.maxConnectionLifeTime(0);
        MongoClientURI mongoClientURI = new MongoClientURI(mongoURI.toString(), build);
        return new MongoClient(mongoClientURI);
    }


    /**
     * @param ip
     * @param port
     * @param username
     * @param password
     * @return true:连接成功，false:连接失败
     */
    public static boolean getMongoConnStatus(String ip, int port, String username, String password) {
        MongoClient client = getMongoClient(ip, port, username, password);
        try {
            MongoIterable<String> names = client.listDatabaseNames();
            names.first();
            client.close();
            return true;
        } catch (Exception e) {
            client.close();
            return false;
        }
    }
}
