package com.tdt.cloud.odm.pojo.task;

import java.io.Serializable;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className TaskDto
 * @description
 * @date 2019-07-05 13:54
 **/

public class TaskDto extends Task implements Serializable {

    /**
     * Mongo连接名称
     */
    private String connName;
    /**
     * 数据库连接地址
     */
    private String ip;
    /**
     * 端口号
     */
    private Integer port;


    public String getConnName() {
        return connName;
    }

    public void setConnName(String connName) {
        this.connName = connName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "connName='" + connName + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
