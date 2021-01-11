package com.tdt.cloud.odm.pojo.json;

import java.io.Serializable;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className BoxParam
 * @description
 * @date 2019-09-23 18:56
 **/

public class BoxParam implements Serializable {

    private double xmin;

    private double ymin;

    private double xmax;

    private double ymax;

    public double getXmin() {
        return xmin;
    }

    public void setXmin(double xmin) {
        this.xmin = xmin;
    }

    public double getYmin() {
        return ymin;
    }

    public void setYmin(double ymin) {
        this.ymin = ymin;
    }

    public double getXmax() {
        return xmax;
    }

    public void setXmax(double xmax) {
        this.xmax = xmax;
    }

    public double getYmax() {
        return ymax;
    }

    public void setYmax(double ymax) {
        this.ymax = ymax;
    }
}
