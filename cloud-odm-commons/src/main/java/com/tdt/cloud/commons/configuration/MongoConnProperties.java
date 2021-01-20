package com.tdt.cloud.commons.configuration;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className MongoConnProperties
 * @description 数据库相关配置
 * @date 2019-04-01 16:40
 **/

public class MongoConnProperties {
    private String collectionName = "tiles";

    private String vectorDBName = "tdt_vector_tile_pbf";

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getVectorDBName() {
        return vectorDBName;
    }

    public void setVectorDBName(String vectorDBName) {
        this.vectorDBName = vectorDBName;
    }
}
