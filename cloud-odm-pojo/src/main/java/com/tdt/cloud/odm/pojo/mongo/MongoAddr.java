package com.tdt.cloud.odm.pojo.mongo;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className MongoAddr
 * @description MongoAddr实体类
 * @date 2019-03-19 17:10
 **/

public class MongoAddr implements Serializable {

    private Integer id;
    /**
     * 连接名称
     */
    @NotBlank(message = "连接名称不能为空")
    private String connName;
    /**
     * 数据库连接地址
     */
    @NotBlank(message = "IP地址不能为空")
    private String ip;
    /**
     * 端口号
     */
    @NotNull(message = "端口号不能为空")
    private Integer port;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 省级行政区划代码
     */
    @NotBlank(message = "省级行政区划不能为空")
    private String provinceCode;

    /**
     * 激活状态：0:未激活，1:激活
     */
    private Integer activate;
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

    public String getConnName() {
        return connName;
    }

    public void setConnName(String connName) {
        this.connName = connName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = StringUtils.isBlank(username) ? "" : username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = StringUtils.isBlank(password) ? "" : password;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Integer getActivate() {
        return activate;
    }

    public void setActivate(Integer activate) {
        this.activate = activate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MongoAddr{" +
                "id=" + id +
                ", connName='" + connName + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", activate=" + activate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
