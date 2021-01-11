package com.tdt.cloud.odm.pojo.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className RangeSpaceModel
 * @description 范围空间实体
 * @date 2019-04-08 16:14
 **/

public class RangeSpaceModel implements Serializable {

    /**
     * 经度最小值
     */
    private Double lonMin;

    /**
     * 经度最大值
     */
    private Double lonMax;

    /**
     * 纬度最小值
     */
    private Double latMin;

    /**
     * 纬度最大值
     */
    private Double latMax;


    private Set<Integer> levels = new HashSet<>();

    /**
     * 图层
     */
    private String lyr;

    /**
     * excel导出时使用字段
     */
    private String pac;

    /**
     * excel导出时使用字段
     */
    private String name;

    public Double getLonMin() {
        return lonMin;
    }

    public void setLonMin(Double lonMin) {
        this.lonMin = lonMin;
    }

    public Double getLonMax() {
        return lonMax;
    }

    public void setLonMax(Double lonMax) {
        this.lonMax = lonMax;
    }

    public Double getLatMin() {
        return latMin;
    }

    public void setLatMin(Double latMin) {
        this.latMin = latMin;
    }

    public Double getLatMax() {
        return latMax;
    }

    public void setLatMax(Double latMax) {
        this.latMax = latMax;
    }

    public String getLyr() {
        return lyr;
    }

    public void setLyr(String lyr) {
        this.lyr = lyr;
    }

    public String getPac() {
        return pac;
    }

    public void setPac(String pac) {
        this.pac = pac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Integer> getLevels() {
        return levels;
    }

    public void setLevels(Set<Integer> levels) {
        this.levels = levels;
    }

    @Override
    public String toString() {
        return "RangeSpaceModel{" +
                "lonMin=" + lonMin +
                ", lonMax=" + lonMax +
                ", latMin=" + latMin +
                ", latMax=" + latMax +
                ", levels=" + levels +
                ", lyr='" + lyr + '\'' +
                ", pac='" + pac + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
