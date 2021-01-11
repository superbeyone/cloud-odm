package com.tdt.cloud.odm.persist;

import com.tdt.cloud.odm.pojo.mongo.MongoDB;
import com.tdt.cloud.odm.pojo.mongo.MongoDBVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className MongoDBMapper
 * @description Mongo数据库Mapper接口
 * @date 2019-03-27 16:35
 **/
@Mapper
@Repository
public interface MongoDBMapper {
    /**
     * 添加新的Mongo数据库信息
     *
     * @param mongoDB
     * @return
     */
    boolean addNewMongoDB(MongoDB mongoDB);

    /**
     * 修改Mongo数据库信息
     *
     * @param mongoDB
     * @return
     */
    boolean updateMongoDB(MongoDB mongoDB);

    /**
     * 根据Mongo连接Id获取该连接下的所有库信息
     *
     * @param id
     * @return
     */
    List<MongoDB> getMongoDBListByConnId(@Param("id") int id);

    /**
     * 查询用户当前所有可操作的数据库权限
     *
     * @param userId
     * @return
     */
    List<MongoDBVo> getMongoDBListOfUserHasAuthByUserId(@Param("userId") int userId);

    /**
     * 根据Mongo连接的主键Id,删除Mongo数据库信息
     *
     * @param connId
     * @return
     */
    boolean deleteMongoDBByConnId(@Param("connId") int connId);

    /**
     * 根据db主键id删除tb_user_db表中的数据
     *
     * @param dbId
     * @return
     */
    boolean deleteUserDBDataByDBId(@Param("dbId") int dbId);

    /**
     * 根据主键删除数据库
     *
     * @param id
     * @return
     */
    boolean deleteMongoDBById(@Param("id") int id);

    /**
     * 根据主键获取数据库信息
     *
     * @param id
     * @return
     */
    MongoDB getMongoDBById(@Param("id") int id);

    /**
     * 根据name获取mongoDB
     *
     * @param name
     * @return
     */
    List<MongoDB> getMongoDBByNameAndConnId(@Param("name") String name, @Param("connId") Integer connId);
}
