package com.tdt.cloud.odm.pojo.mongo;

import java.io.Serializable;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className MongoDBVo
 * @description
 * @date 2019-03-29 09:35
 **/

public class MongoDBVo implements Serializable {
    /**
     * Mongo库表主键
     */
    private Integer dbId;
    /**
     * 库名称
     */
    private String dbName;

    /**
     * 库别名
     */
    private String dbAlias;

    /**
     * 库类型
     */
    private Integer type;

    public Integer getDbId() {
        return dbId;
    }

    public void setDbId(Integer dbId) {
        this.dbId = dbId;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbAlias() {
        return dbAlias;
    }

    public void setDbAlias(String dbAlias) {
        this.dbAlias = dbAlias;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MongoDBVo{" +
                "dbId=" + dbId +
                ", dbName='" + dbName + '\'' +
                ", dbAlias='" + dbAlias + '\'' +
                ", type=" + type +
                '}';
    }
}
