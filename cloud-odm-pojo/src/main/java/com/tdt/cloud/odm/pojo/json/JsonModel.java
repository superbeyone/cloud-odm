package com.tdt.cloud.odm.pojo.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className JsonModel
 * @description json文件实体映射
 * @date 2019-09-23 18:53
 **/

public class JsonModel implements Serializable {

    private String tileid;

    private List<ContentParam> content = new ArrayList<>();

    public String getTileid() {
        return tileid;
    }

    public void setTileid(String tileid) {
        this.tileid = tileid;
    }

    public List<ContentParam> getContent() {
        return content;
    }

    public void setContent(List<ContentParam> content) {
        this.content = content;
    }
}
