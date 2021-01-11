package com.tdt.cloud.odm.service;


import com.tdt.cloud.odm.pojo.mongo.MongoAddr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className MongoConnectionOperatorService
 * @description Mongo连接Service接口
 * @date 2019-03-19 17:04
 **/
public interface MongoConnectionService {
    /**
     * 添加新的Mongo数据库连接地址信息
     *
     * @param mongoAddr Mongo数据库连接信息实体
     * @return true：添加成功，false：添加失败
     */
    boolean addNewMongoConnectionAddr(MongoAddr mongoAddr);


    /**
     * 获取管理员Mongo连接地址
     *
     * @return
     */
    ArrayList<MongoAddr> getAdminMongoConnectionAddrList();

    /**
     * 获取用户Mongo连接地址
     *
     * @return
     */
    ArrayList<MongoAddr> getUserMongoConnectionAddrList();

    /**
     * 根据主键Id更改mongo连接为激活状态（超级管理员使用）
     *
     * @param id
     * @return
     */
    boolean updateMongoConnectionStatusToActivateById(int id);

    /**
     * 根据主键Id更改mongo连接为未激活状态（超级管理员使用）
     *
     * @param id
     * @return
     */
    boolean updateMongoConnectionStatusToNotActivateById(int id);

    /**
     * 根据行政区划编码获取已激活的Mongo连接
     *
     * @param provinceCode
     * @return
     */
    MongoAddr getActivateMongoConnectionByUserProvinceCode(String provinceCode);

    /**
     * 根据主键删除mongo连接信息
     *
     * @param id
     * @return
     */
    boolean deleteMongoConnectionAddrById(int id);


    /**
     * 获取所有的未激活状态的Mongo连接（超级管理员使用）
     *
     * @return
     */
    List<MongoAddr> getAllUnActivateMongoConnection();

    /**
     * 根据行政区划，获取该行政区划下的所有Mongo连接地址
     *
     * @param provinceCode
     * @return
     */
    List<MongoAddr> getUserMongoConnectionAddrListByProvinceCode(String provinceCode);

    /**
     * 根据主键获取Mongo连接信息
     *
     * @param id
     * @return
     */
    MongoAddr getMongoConnectionAddrById(int id);

    /**
     * 更新连接信息
     *
     * @param mongoAddr
     * @return
     */
    boolean updateMongoConnection(MongoAddr mongoAddr);
}
