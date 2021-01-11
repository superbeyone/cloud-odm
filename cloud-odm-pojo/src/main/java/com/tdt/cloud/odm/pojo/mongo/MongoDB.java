package com.tdt.cloud.odm.pojo.mongo;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className MongoDB
 * @description 新建数据库MongoDB实体
 * @date 2019-03-21 16:14
 **/

public class MongoDB implements Serializable {


    private Integer id;
    /**
     * 数据库名
     */
    @NotBlank(message = "数据库名称不能为空")
    private String name;

    /**
     * 中文名称
     */
    private String alias;

    /**
     * Mongo连接地址Id
     */
    private Integer connId;

    /**
     * 类型：0:普通，1:多时相
     */
    private Integer type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getConnId() {
        return connId;
    }

    public void setConnId(Integer connId) {
        this.connId = connId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
