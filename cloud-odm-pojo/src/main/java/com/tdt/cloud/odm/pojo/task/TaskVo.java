package com.tdt.cloud.odm.pojo.task;

import java.io.Serializable;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className TaskVo
 * @description 任务信息返回视图
 * @date 2019-05-20 10:33
 **/

public class TaskVo extends Task implements Serializable {

    private String cordName;

    private String cordRemark;

    public String getCordName() {
        return cordName;
    }

    public void setCordName(String cordName) {
        this.cordName = cordName;
    }

    public String getCordRemark() {
        return cordRemark;
    }

    public void setCordRemark(String cordRemark) {
        this.cordRemark = cordRemark;
    }

    @Override
    public String toString() {
        return "TaskVo{" +
                "cordName='" + cordName + '\'' +
                ", cordRemark='" + cordRemark + '\'' +
                '}';
    }
}
