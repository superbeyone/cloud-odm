package com.tdt.cloud.odm.pojo.data;

import java.io.Serializable;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className ShapeModel
 * @description Shape实体类映射
 * @date 2019-04-02 10:22
 **/

public class ShapeModel implements Serializable {

    private String id;

    private String type;

    private String geometry;

    private String properties;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
