package com.tdt.cloud.odm.pojo.mongo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className MongoDBDto
 * @description
 * @date 2019-03-28 16:43
 **/

public class MongoAddrDBVo extends MongoAddr implements Serializable {

    private List<MongoDBVo> mongoDBVos;


    public List<MongoDBVo> getMongoDBVos() {
        return mongoDBVos;
    }

    public void setMongoDBVos(List<MongoDBVo> mongoDBVos) {
        this.mongoDBVos = mongoDBVos;
    }

    @Override
    public String toString() {
        return "MongoAddrDBVo{" +
                "mongoDBVos=" + mongoDBVos +
                '}';
    }
}
