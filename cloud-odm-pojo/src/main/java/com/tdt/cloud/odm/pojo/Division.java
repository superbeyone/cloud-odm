package com.tdt.cloud.odm.pojo;

import java.io.Serializable;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className Division
 * @description 行政区划信息实体类
 * @date 2019-03-20 13:10
 **/

public class Division implements Serializable {
    /**
     * 行政区划
     */
    private String code;

    /**
     * 省市名称
     */
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
