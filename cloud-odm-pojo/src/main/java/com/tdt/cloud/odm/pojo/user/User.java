package com.tdt.cloud.odm.pojo.user;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className User
 * @description 用户实体
 * @date 2019-03-20 14:38
 **/

public class User implements Serializable {


    private Integer id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 行政区划
     */
    @NotBlank(message = "行政区划不能为空")
    private String provinceCode;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 角色 值为1时代表为管理员  超级管理员(role=1 && provinceCode=00)
     */
    private Integer role;

    /**
     * 昵称
     */
    private String nickname;
    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 挂载根路径
     */
    private String rootPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", nickname='" + nickname + '\'' +
                ", createTime=" + createTime +
                ", rootPath='" + rootPath + '\'' +
                '}';
    }
}
