package com.tdt.cloud.odm.pojo.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className ContentParam
 * @description
 * @date 2019-09-23 18:55
 **/

public class ContentParam implements Serializable {

    private List<BoxParam> pbox;

    public List<BoxParam> getPbox() {
        return pbox;
    }

    public void setPbox(List<BoxParam> pbox) {
        this.pbox = pbox;
    }
}
