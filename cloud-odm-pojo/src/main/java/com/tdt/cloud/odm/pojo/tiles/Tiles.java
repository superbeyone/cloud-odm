package com.tdt.cloud.odm.pojo.tiles;

import com.sun.corba.se.spi.ior.ObjectId;

import java.io.Serializable;

/**
 * 瓦片实体
 *
 * @author ack
 * <p>
 * <code>
 * {
 * "_id" : ObjectId("5b610dacca89c221c02f1bb4"),
 * "filename" : "11_1624_282.png",
 * "lyr" : "img",
 * "prj" : "c",
 * "t" : "20151228",
 * "x" : "1624",
 * "y" : "282",
 * "l" : "11",
 * "length" : 177,
 * "md5" : "95b04934270a92fd6d4abf2ac1f50289",
 * "createTime" : "2018-08-01 09:32:28",
 * "contentType" : "image/png",
 * "data" : BinData(0,"iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAMAAABrrFhUAAAAA1BMVEX19O4jF2cnAAAACXBIWXMAAA7EAAAOxAGVKw4bAAAAVElEQVR4nO3BAQEAAACAkP6v7ggKAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAGAEPAAFN9soGAAAAAElFTkSuQmCC")
 * }
 * </code>
 * </p>
 */
public class Tiles implements Serializable {

    private static final long serialVersionUID = -7964061395661912558L;
    private ObjectId id;
    private String filename;
    private String lyr;
    private String prj;
    private String t;
    private String x;
    private String y;
    private String l;
    private Long length;
    private String md5;
    private String createTime;
    private String contentType;
    private String data;
    private String format = "tiles";
    private String time;

    public ObjectId getId() {
        return this.id;
    }

    public void setId(ObjectId _id) {
        this.id = _id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getLyr() {
        return lyr;
    }

    public void setLyr(String lyr) {
        this.lyr = lyr;
    }

    public String getPrj() {
        return prj;
    }

    public void setPrj(String prj) {
        this.prj = prj;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCollectionName() {
        if (null != this.getTime()) {
            return "tiles:" + this.getTime();
        }
        return "tiles:";
    }

    public String getDBName() {
        return this.getT();
    }

    @Override
    public String toString() {
        return "Tiles{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", lyr='" + lyr + '\'' +
                ", prj='" + prj + '\'' +
                ", t='" + t + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", l='" + l + '\'' +
                ", length=" + length +
                ", md5='" + md5 + '\'' +
                ", createTime='" + createTime + '\'' +
                ", contentType='" + contentType + '\'' +
                ", data='" + data + '\'' +
                ", format='" + format + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
