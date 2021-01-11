package com.tdt.cloud.odm.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className OperateLogVo
 * @description 操作日志VO对象
 * @date 2019-05-05 18:03
 **/

public class OperateLogVo implements Serializable {

    /**
     * 主键Id
     */
    private Integer id;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 任务组名称
     */
    private String cordName;

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
     * 状态
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    public String getCordName() {
        return cordName;
    }

    public void setCordName(String cordName) {
        this.cordName = cordName;
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
}
