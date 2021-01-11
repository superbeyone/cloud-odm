package com.tdt.cloud.odm.pojo.task;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className Task
 * @description Task实体类
 * @date 2019-03-21 09:27
 **/

public class Task implements Serializable {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 任务组Id
     */
    private Integer cordId;

    /**
     * 用户行政区划编码，关联信息
     */
    private String provinceCode;

    /**
     * 执行线程数
     */
    private Integer threadCount = 2;

    /**
     * 数据库主键
     */
    private Integer dbId;
    /**
     * 连接地址主键
     */
    private Integer connId;

    /**
     * 用户主键Id
     */
    private Integer userId;
    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 是否为强匹配 文件后缀名
     */
    private String strongMatch;

    /**
     * 数据导入：数据源路径
     * 数据更新：数据源路径
     * 数据删除：
     * 1.按照范围删除 -> 坐标点拼接的字符串
     * 2.按照txt或shp或源数据删除 -> 文件全路径
     * 数据提取：提取范围的excel路径
     */
    private String paramVal;

    /**
     * 数据存放路径
     */
    private String savePath;

    /**
     * 打包路径
     */
    private String packPath;
    /**
     * 流水号
     */
    private String serialNum;

    /**
     * 任务类型
     */
    private String operateType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 操作人
     */
    private String operatorName;

    /**
     * 当前任务状态   状态 0:为就绪状态，1:为运行状态，2：为成功状态，-1：为失败状态
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sequence;

    /**
     * 执行结果
     */
    private String result;
    /**
     * 备注
     */
    private String remark;

    /**
     * 评估执行时间
     */
    private Long predictTime;

    /**
     * 预读结果
     */
    private Integer prefetch;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCordId() {
        return cordId;
    }

    public Integer getConnId() {
        return connId;
    }

    public void setConnId(Integer connId) {
        this.connId = connId;
    }

    public void setCordId(Integer cordId) {
        this.cordId = cordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Integer getDbId() {
        return dbId;
    }

    public void setDbId(Integer dbId) {
        this.dbId = dbId;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getStrongMatch() {
        return strongMatch;
    }

    public void setStrongMatch(String strongMatch) {
        this.strongMatch = strongMatch;
    }

    public String getParamVal() {
        return paramVal;
    }

    public void setParamVal(String paramVal) {
        this.paramVal = paramVal;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getPackPath() {
        return packPath;
    }

    public void setPackPath(String packPath) {
        this.packPath = packPath;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }

    public Long getPredictTime() {
        return predictTime;
    }

    public void setPredictTime(Long predictTime) {
        this.predictTime = predictTime;
    }

    public Integer getPrefetch() {
        return prefetch;
    }

    public void setPrefetch(Integer prefetch) {
        this.prefetch = prefetch;
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", cordId=" + cordId +
                ", provinceCode='" + provinceCode + '\'' +
                ", threadCount=" + threadCount +
                ", dbId=" + dbId +
                ", connId=" + connId +
                ", userId=" + userId +
                ", dbName='" + dbName + '\'' +
                ", strongMatch='" + strongMatch + '\'' +
                ", paramVal='" + paramVal + '\'' +
                ", savePath='" + savePath + '\'' +
                ", packPath='" + packPath + '\'' +
                ", serialNum='" + serialNum + '\'' +
                ", operateType='" + operateType + '\'' +
                ", createTime=" + createTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", operatorName='" + operatorName + '\'' +
                ", status=" + status +
                ", sequence=" + sequence +
                ", result='" + result + '\'' +
                ", remark='" + remark + '\'' +
                ", predictTime=" + predictTime +
                ", prefetch=" + prefetch +
                '}';
    }
}
