package com.tdt.cloud.odm.pojo.grid;

import java.io.Serializable;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className RowAndColModel
 * @description 行列号数据集
 * @date 2020-12-03 14:46
 **/

public class RowAndColModel implements Serializable {


    private static final long serialVersionUID = 8419915728277846239L;

    private int x;

    private int y;

    private int l;

    public RowAndColModel() {
    }

    public RowAndColModel(int x, int y, int l) {
        this.x = x;
        this.y = y;
        this.l = l;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

}
