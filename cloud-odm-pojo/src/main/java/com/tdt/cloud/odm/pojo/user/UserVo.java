package com.tdt.cloud.odm.pojo.user;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className UserVo
 * @description
 * @date 2019-05-06 17:05
 **/

public class UserVo extends User {
    /**
     * 行政区划
     */
    private String division;

    private String roleVo;

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getRoleVo() {
        return roleVo;
    }

    public void setRoleVo(String roleVo) {
        this.roleVo = roleVo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + super.getId() +
                ", username='" + super.getUsername() + '\'' +
                ", provinceCode='" + super.getProvinceCode() + '\'' +
                ", mobile='" + super.getMobile() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", role=" + super.getRole() +
                ", nickname='" + super.getNickname() + '\'' +
                ", createTime=" + super.getCreateTime() +
                ", rootPath='" + super.getRootPath() + '\'' +
                "division='" + division + '\'' +
                ", roleVo='" + roleVo + '\'' +
                '}';
    }
}
