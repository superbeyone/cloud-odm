package com.tdt.cloud.odm.pojo.data;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className ImageModel
 * @description 图片Model
 * @date 2019-04-18 13:03
 **/

public class ImageModel {

    private String contentType;

    private int length;

    private String filename;

    private String md5;

    private String data;

    private Object createTime;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }
}
