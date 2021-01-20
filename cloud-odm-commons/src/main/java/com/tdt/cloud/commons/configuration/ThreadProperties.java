package com.tdt.cloud.commons.configuration;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className ThreadProperties
 * @description
 * @date 2020-01-10 16:23
 **/

public class ThreadProperties {

    private int min = 2;

    private int max = 8;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
