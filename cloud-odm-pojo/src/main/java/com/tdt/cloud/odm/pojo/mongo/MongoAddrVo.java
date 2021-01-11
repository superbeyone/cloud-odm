package com.tdt.cloud.odm.pojo.mongo;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className MongoAddrVo
 * @description 用户可见实体
 * @date 2019-04-18 10:46
 **/

public class MongoAddrVo {
    /**
     * Mongo连接表主键
     */
    private Integer id;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        return "MongoAddrVo{" +
                "id=" + id +
                ", connName='" + connName + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
