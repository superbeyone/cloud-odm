package com.tdt.cloud.odm.pojo.task;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className TaskGroup
 * @description 任务组实体类
 * @date 2019-03-21 09:28
 **/

public class TaskCord implements Serializable {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户主键Id
     */
    private Integer userId;

    /**
     * 任务组名称
     */
    private String name;


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
     * 状态 0:为就绪状态，1:为运行状态，2：为成功状态，-1：为失败状态
     */
    private Integer status;

    /**
     * 行政区划编码，用于关联用户
     */
    private String provinceCode;
    /**
     * 任务组内的任务数量
     */
    private Integer count;

    /**
     * 操作人
     */
    private String operatorName;

    /**
     * 等等待执行队列，0：不等待，大于0时，进入执行排队队列，优先级从小到大
     */
    private Integer waitQueue;

    /**
     * 备注
     */
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getWaitQueue() {
        return waitQueue;
    }

    public void setWaitQueue(Integer waitQueue) {
        this.waitQueue = waitQueue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "TaskCord{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", provinceCode='" + provinceCode + '\'' +
                ", count=" + count +
                ", operatorName='" + operatorName + '\'' +
                ", waitQueue=" + waitQueue +
                ", remark='" + remark + '\'' +
                '}';
    }
}
