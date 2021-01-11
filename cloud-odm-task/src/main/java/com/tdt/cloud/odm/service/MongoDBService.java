package com.tdt.cloud.odm.service;


import com.tdt.cloud.odm.pojo.mongo.MongoDB;
import com.tdt.cloud.odm.pojo.mongo.MongoDBVo;

import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className MongoDBService
 * @description MongoDB service
 * @date 2019-03-27 16:33
 **/

public interface MongoDBService {

    /**
     * 添加新的Mongo数据库
     *
     * @param mongoDB Mongo数据库实体
     * @return
     */
    boolean addNewMongoDB(MongoDB mongoDB);


    /**
     * 修改Mongo数据库
     *
     * @param mongoDB Mongo数据库实体
     * @return
     */
    boolean updateMongoDB(MongoDB mongoDB);

    /**
     * 根据Mongo连接Id获取该连接下的所有库信息
     *
     * @param id
     * @return
     */
    List<MongoDB> getMongoDBListByConnId(int id);

    /**
     * 查询用户当前所有可操作的数据库权限
     *
     * @param userId
     * @return
     */
    List<MongoDBVo> getMongoDBListOfUserHasAuthByUserId(int userId);

    /**
     * 根据Mongo连接的主键Id,删除Mongo数据库信息
     *
     * @param connId
     * @return
     */
    boolean deleteMongoDBByConnId(int connId);

    /**
     * 根据db主键id删除tb_user_db表中的数据
     *
     * @param dbId
     * @return
     */
    boolean deleteUserDBDataByDBId(int dbId);

    /**
     * 根据主键删除数据库信息
     *
     * @param id
     * @return
     */
    boolean deleteMongoDBById(int id);

    /**
     * 根据主键获取数据库信息
     *
     * @param id
     * @return
     */
    MongoDB getMongoDBById(int id);

    /**
     * 根据dbName获取MongoDB
     *
     * @param name
     * @return
     */
    List<MongoDB> getMongoDBByNameAndConnId(String name, Integer connId);
}
