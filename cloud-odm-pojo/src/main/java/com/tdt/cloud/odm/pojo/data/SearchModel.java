package com.tdt.cloud.odm.pojo.data;

import java.io.Serializable;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className SearchModel
 * @description 多条件搜索实体
 * @date 2019-04-11 11:09
 **/

public class SearchModel implements Serializable {

    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 流水号
     */
    private String serialNum;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 行政区划编码
     */
    private String provinceCode;

    /**
     * 用户名
     */
    private String username;

    /**
     * 任务组名称
     */
    private String cordName;
    /**
     * 用户主键
     */
    private Integer userId;

    /**
     * 操作类型
     */
    private String operateType;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCordName() {
        return cordName;
    }

    public void setCordName(String cordName) {
        this.cordName = cordName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    @Override
    public String toString() {
        return "SearchModel{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", serialNum='" + serialNum + '\'' +
                ", status=" + status +
                ", provinceCode='" + provinceCode + '\'' +
                ", username='" + username + '\'' +
                ", cordName='" + cordName + '\'' +
                ", userId=" + userId +
                ", operateType='" + operateType + '\'' +
                '}';
    }
}
