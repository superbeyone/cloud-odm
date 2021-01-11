package com.tdt.cloud.odm.persist;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className TableInfoMapper
 * @description
 * @date 2020-04-24 16:31
 **/
@Mapper
@Repository
public interface TableInfoMapper {

    /**
     * 创建表
     *
     * @param tableName   表名
     * @param filedString 字段str
     * @return 结果
     */
    boolean createTable(@Param("tableName") String tableName, @Param("filedString") String filedString);

    /**
     * 判断物理表是否存在
     *
     * @param tableName 表名
     * @return 表名
     */
    String isTableExistInDB(@Param("tableName") String tableName);

    /**
     * 入库
     *
     * @param tableName   表名
     * @param fieldString 字段
     * @param filedValues 值
     * @return 结果
     */
    boolean insertRegion(String tableName, String fieldString, List<Object> filedValues);

    /**
     * 删除表
     *
     * @param tableName 表名
     * @return 结果
     */
    void dropTableByTableName(@Param("tableName") String tableName);
}
