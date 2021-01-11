package com.tdt.cloud.odm.pojo.tiles;

import java.io.Serializable;

/**
 * wmts封装
 */
public class WmtsTiles extends Tiles implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3513999763903774161L;

    private String service = "WMTS";                                      // 服务
    private String request;                                               // 操作名 getTile or getCabpabilities
    private String version = "1.00";                                      // 版本 1.00
    private String layer;                                                 // 图层
    private String style;                                                 // 类型
    private String tilematrixset;                                         // 投影方式
    public static final String WMTS_CAP_FILE_NAME_SUFIX = "tdt_wmts_";    // 文件前缀名称
    private String tilecol;
    private String tilerow;
    private String tilematrix;


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTilematrixset() {
        return tilematrixset;
    }

    public void setTilematrixset(String tilematrixset) {
        this.tilematrixset = tilematrixset;
    }

    public String getTilecol() {
        return tilecol;
    }

    public void setTilecol(String tilecol) {
        this.tilecol = tilecol;
    }

    public String getTilerow() {
        return tilerow;
    }

    public void setTilerow(String tilerow) {
        this.tilerow = tilerow;
    }

    public String getTilematrix() {
        return tilematrix;
    }

    public void setTilematrix(String tilematrix) {
        this.tilematrix = tilematrix;
    }
}
