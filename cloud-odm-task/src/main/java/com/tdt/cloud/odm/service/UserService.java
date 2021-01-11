package com.tdt.cloud.odm.service;


import com.tdt.cloud.odm.pojo.user.User;

import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className UserService
 * @description
 * @date 2019-03-20 14:53
 **/

public interface UserService {

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    Integer addUser(User user);

    /**
     * 修改用户基本信息
     *
     * @param user
     * @return
     */
    boolean updateUserBasicInfo(User user);

    /**
     * 根据用户名修改用户密码
     *
     * @param username 用户名
     * @param password 新密码
     * @return
     */
    boolean updateUserPwdByUsername(String username, String password);

    /**
     * 根据用户名获取用户基本信息（不包含密码）
     *
     * @param username
     * @return
     */
    User getUserBasicInfoByUsername(String username);

    /**
     * 根据用户名获取用户登录密码
     *
     * @param username
     * @return
     */
    String getUserPasswordByUsername(String username);

    /**
     * 清除对用户关于数据库操作的所有授权
     *
     * @param userId
     * @return
     */
    boolean deleteUserOfAllOperateDBAuthDataByUserId(int userId);


    /**
     * 给用户对于具体数据库操作授权
     *
     * @param userId
     * @param dbIds
     * @return
     */
    boolean addUserOfOperateDBAuthData(Integer userId, List<Integer> dbIds);

    /**
     * 获取用户总数量
     *
     * @param kwd
     * @return
     */
    int getUserCount(String kwd);

    /**
     * 分页展示用户信息
     *
     * @param kwd
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<User> getUserListByPage(String kwd, int pageNo, int pageSize);

    /**
     * 重置用户密码
     *
     * @param id
     * @return
     */
    boolean resetUserPasswordByUserId(Integer id);

    /**
     * 根据用户行政区划获取用户列表
     *
     * @param provinceCode
     * @return
     */
    List<User> getUserListByProvinceCode(String provinceCode);

    /**
     * 根据主键获取用户信息
     *
     * @param id
     * @return
     */
    User getUserBasicInfoById(Integer id);

    /**
     * 根据用户主键删除用户
     *
     * @param id
     * @return
     */
    boolean deleteUserById(Integer id);
}
