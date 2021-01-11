package com.tdt.cloud.odm.service.impl;

import com.tdt.cloud.odm.persist.UserMapper;
import com.tdt.cloud.odm.pojo.user.User;
import com.tdt.cloud.odm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className UserServiceImpl
 * @description
 * @date 2019-03-20 14:53
 **/
@Service
public class UserServiceImpl implements UserService {


    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    UserMapper userMapper;

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    /**
     * 修改用户基本信息
     *
     * @param user
     * @return
     */
    @Override
    public boolean updateUserBasicInfo(User user) {

        return userMapper.updateUserBasicInfo(user);
    }

    /**
     * 根据用户名修改用户密码
     *
     * @param username 用户名
     * @param password 新密码
     * @return
     */
    @Override
    public boolean updateUserPwdByUsername(String username, String password) {
        return userMapper.updateUserPwdByUsername(username, password);
    }

    /**
     * 根据用户名获取用户基本信息
     *
     * @param username
     * @return
     */
    @Override
    public User getUserBasicInfoByUsername(String username) {
        return userMapper.getUserBasicInfoByUsername(username);
    }

    /**
     * 根据用户名获取用户登录密码
     *
     * @param username
     * @return
     */
    @Override
    public String getUserPasswordByUsername(String username) {
        return userMapper.getUserPasswordByUsername(username);
    }

    /**
     * 清除对用户关于数据库操作的所有授权
     *
     * @param userId
     * @return
     */
    @Override
    public boolean deleteUserOfAllOperateDBAuthDataByUserId(int userId) {
        return userMapper.deleteUserOfAllOperateDBAuthDataByUserId(userId);
    }


    /**
     * 给用户对于具体数据库操作授权
     *
     * @param userId
     * @param dbIds
     * @return
     */
    @Override
    public boolean addUserOfOperateDBAuthData(Integer userId, List<Integer> dbIds) {
        return userMapper.addUserOfOperateDBAuthData(userId, dbIds);
    }

    /**
     * 获取用户总数量
     *
     * @return
     */
    @Override
    public int getUserCount(String kwd) {
        return userMapper.getUserCount(kwd);
    }

    /**
     * 分页展示用户信息
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<User> getUserListByPage(String kwd, int pageNo, int pageSize) {
        return userMapper.getUserListByPage(kwd, (pageNo - 1) * pageSize, pageSize);
    }

    /**
     * 重置用户密码
     *
     * @param id
     * @return
     */
    @Override
    public boolean resetUserPasswordByUserId(Integer id) {
        return userMapper.resetUserPasswordByUserId(id);
    }

    /**
     * 根据用户行政区划获取用户列表
     *
     * @param provinceCode
     * @return
     */
    @Override
    public List<User> getUserListByProvinceCode(String provinceCode) {
        return userMapper.getUserListByProvinceCode(provinceCode);
    }

    /**
     * 根据主键获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public User getUserBasicInfoById(Integer id) {
        return userMapper.getUserBasicInfoById(id);
    }

    /**
     * 根据用户主键删除用户
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }
}
