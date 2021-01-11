
package com.tdt.cloud.odm.pojo.tiles;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 多时相信息
 *
 * @author ack
 */

public class TimePhase implements Serializable {

    private static final long serialVersionUID = 6676296605761168605L;

    private Integer id;                            // 数据库id
    private String layer;                          // 图层
    private String projection;                     // 投影方式
    private String region;                         // 地区名称
    private String regionCode;                     // 地区编码
    private Integer level;                         // 等级
    private String geom;                           // 多时相范围(数据库类型为(Type))
    private String time;                           //时相时间
    private Date createTime;                       // 记录创建时间
    /*Polygon condition*/
    private String coordinatesString;
    private List<Double[]> points;                 // polygon坐标
    //private GeoJson<?> geoJson;                    // spring-data-mongo polygon


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getProjection() {
        return projection;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCoordinatesString() {
        return coordinatesString;
    }

    public void setCoordinatesString(String coordinates) {
        this.coordinatesString = coordinates;
    }

    public List<Double[]> getPoints() {
        return points;
    }

    public void setPoints(List<Double[]> points) {
        this.points = points;
    }

    /*public GeoJson<?> getGeoJson() {
        return geoJson;
    }
    public void setGeoJson(GeoJson<?> geoJson) {
        this.geoJson = geoJson;
    }*/
    @Override
    public String toString() {
        String s = "{"
                + "layer : " + layer
                + ", projection : " + projection
                + ", region : " + region
                + ", regionCode : " + regionCode
                + ", level : " + level
                + ", time : " + time
                + ", geom : " + geom
                + "}";
        return s;
    }

}
