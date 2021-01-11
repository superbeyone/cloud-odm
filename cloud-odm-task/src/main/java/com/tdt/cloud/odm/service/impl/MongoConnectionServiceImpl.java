package com.tdt.cloud.odm.service.impl;

import com.tdt.cloud.odm.persist.MongoConnectionMapper;
import com.tdt.cloud.odm.pojo.mongo.MongoAddr;
import com.tdt.cloud.odm.service.MongoConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className MongoConnectionServiceImpl
 * @description mongo连接地址业务层实现
 * @date 2019-03-19 17:05
 **/
@Service
public class MongoConnectionServiceImpl implements MongoConnectionService {

    @Autowired
    MongoConnectionMapper mongoConnectionMapper;

    /**
     * 添加新的Mongo数据库连接地址信息
     *
     * @param mongoAddr Mongo数据库信息实体
     * @return true：添加成功，false：添加失败
     */
    @Override
    public boolean addNewMongoConnectionAddr(MongoAddr mongoAddr) {

        return mongoConnectionMapper.addNewMongoConnectionAddr(mongoAddr);
    }


    /**
     * 获取所有的未激活状态的Mongo连接（超级管理员使用）
     *
     * @return
     */
    @Override
    public List<MongoAddr> getAllUnActivateMongoConnection() {
        return mongoConnectionMapper.getAllUnActivateMongoConnection();
    }

    /**
     * 根据行政区划，获取该行政区划下的所有Mongo连接地址
     *
     * @param provinceCode
     * @return
     */
    @Override
    public List<MongoAddr> getUserMongoConnectionAddrListByProvinceCode(String provinceCode) {
        return mongoConnectionMapper.getUserMongoConnectionAddrListByProvinceCode(provinceCode);
    }

    /**
     * 根据主键获取Mongo连接信息
     *
     * @param id
     * @return
     */
    @Override
    public MongoAddr getMongoConnectionAddrById(int id) {
        return mongoConnectionMapper.getMongoConnectionAddrById(id);
    }

    /**
     * 获取管理员Mongo连接地址
     *
     * @return
     */
    @Override
    public ArrayList<MongoAddr> getAdminMongoConnectionAddrList() {
        return mongoConnectionMapper.getAdminMongoConnectionAddrList();
    }

    /**
     * 获取Mongo连接地址
     *
     * @return
     */
    @Override
    public ArrayList<MongoAddr> getUserMongoConnectionAddrList() {
        return mongoConnectionMapper.getUserMongoConnectionAddrList();
    }

    /**
     * 根据主键Id更改mongo连接为激活状态（超级管理员使用）
     *
     * @param id
     * @return
     */
    @Override
    public boolean updateMongoConnectionStatusToActivateById(int id) {
        return mongoConnectionMapper.updateMongoConnectionStatusToActivateById(id);
    }

    /**
     * 根据主键Id更改mongo连接为未激活状态（超级管理员使用）
     *
     * @param id
     * @return
     */
    @Override
    public boolean updateMongoConnectionStatusToNotActivateById(int id) {
        return mongoConnectionMapper.updateMongoConnectionStatusToNotActivateById(id);
    }

    /**
     * 根据行政区划编码获取已激活的Mongo连接
     *
     * @param provinceCode
     * @return
     */
    @Override
    public MongoAddr getActivateMongoConnectionByUserProvinceCode(String provinceCode) {
        return mongoConnectionMapper.getActivateMongoConnectionByUserProvinceCode(provinceCode);
    }

    /**
     * 根据主键删除mongo连接信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteMongoConnectionAddrById(int id) {
        return mongoConnectionMapper.deleteMongoConnectionAddrById(id);
    }

    /**
     * 更新连接信息
     *
     * @param mongoAddr
     * @return
     */
    @Override
    public boolean updateMongoConnection(MongoAddr mongoAddr) {
        return mongoConnectionMapper.updateMongoConnection(mongoAddr);
    }
}
