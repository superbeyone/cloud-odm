package com.tdt.cloud.odm.service.impl;

import com.tdt.cloud.odm.persist.MongoDBMapper;
import com.tdt.cloud.odm.pojo.mongo.MongoDB;
import com.tdt.cloud.odm.pojo.mongo.MongoDBVo;
import com.tdt.cloud.odm.service.MongoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className MongoDBServiceImpl
 * @description
 * @date 2019-03-27 16:34
 **/
@Service
public class MongoDBServiceImpl implements MongoDBService {

    @Autowired
    MongoDBMapper mongoDBMapper;

    /**
     * 添加新的Mongo数据库
     *
     * @param mongoDB Mongo数据库实体
     * @return
     */
    @Override
    public boolean addNewMongoDB(MongoDB mongoDB) {

        return mongoDBMapper.addNewMongoDB(mongoDB);
    }

    /**
     * 修改Mongo数据库
     *
     * @param mongoDB Mongo数据库实体
     * @return
     */
    @Override
    public boolean updateMongoDB(MongoDB mongoDB) {
        return mongoDBMapper.updateMongoDB(mongoDB);
    }

    /**
     * 根据Mongo连接的主键Id,删除Mongo数据库信息
     *
     * @param connId
     * @return
     */
    @Override
    public boolean deleteMongoDBByConnId(int connId) {
        return mongoDBMapper.deleteMongoDBByConnId(connId);
    }


    /**
     * 根据Mongo连接Id获取该连接下的所有库信息
     *
     * @param id
     * @return
     */
    @Override
    public List<MongoDB> getMongoDBListByConnId(int id) {
        return mongoDBMapper.getMongoDBListByConnId(id);
    }

    /**
     * 查询用户当前所有可操作的数据库权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<MongoDBVo> getMongoDBListOfUserHasAuthByUserId(int userId) {
        return mongoDBMapper.getMongoDBListOfUserHasAuthByUserId(userId);
    }

    /**
     * 根据db主键id删除tb_user_db表中的数据
     *
     * @param dbId
     * @return
     */
    @Override
    public boolean deleteUserDBDataByDBId(int dbId) {
        return mongoDBMapper.deleteUserDBDataByDBId(dbId);
    }

    /**
     * 根据主键删除数据库信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteMongoDBById(int id) {
        return mongoDBMapper.deleteMongoDBById(id);
    }

    /**
     * 根据主键获取数据库信息
     *
     * @param id
     * @return
     */
    @Override
    public MongoDB getMongoDBById(int id) {
        return mongoDBMapper.getMongoDBById(id);
    }

    /**
     * 根据dbName获取MongoDB
     *
     * @param name
     * @return
     */
    @Override
    public List<MongoDB> getMongoDBByNameAndConnId(String name,Integer connId) {
        return mongoDBMapper.getMongoDBByNameAndConnId(name,connId);
    }
}
