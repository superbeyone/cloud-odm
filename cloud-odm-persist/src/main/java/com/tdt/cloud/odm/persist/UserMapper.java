package com.tdt.cloud.odm.persist;

import com.tdt.cloud.odm.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className UserOperatorMapper
 * @description 用户数据操作接口
 * @date 2019-03-20 14:56
 **/
@Mapper
@Repository
public interface UserMapper {

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
     * 根据用户名修改密码
     *
     * @param username
     * @param password
     * @return
     */
    boolean updateUserPwdByUsername(@Param("username") String username, @Param("password") String password);

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
     * 给用户对于具体数据库操作授权
     *
     * @param userId
     * @param dbIds
     * @return
     */
    boolean addUserOfOperateDBAuthData(@Param("userId") Integer userId, @Param("dbIds") List<Integer> dbIds);


    /**
     * 清除对用户关于数据库操作的所有授权
     *
     * @param userId
     * @return
     */
    boolean deleteUserOfAllOperateDBAuthDataByUserId(@Param("userId") int userId);

    /**
     * 获取用户总数量
     *
     * @param kwd
     * @return
     */
    int getUserCount(@Param("kwd") String kwd);

    /**
     * 分页展示用户信息
     *
     * @param kwd
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<User> getUserListByPage(@Param("kwd") String kwd, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    /**
     * 重置用户密码
     *
     * @param id
     * @return
     */
    boolean resetUserPasswordByUserId(@Param("id") Integer id);

    /**
     * 根据用户行政区划获取用户列表
     *
     * @param provinceCode
     * @return
     */
    List<User> getUserListByProvinceCode(@Param("provinceCode") String provinceCode);

    /**
     * 根据主键获取用户信息
     *
     * @param id
     * @return
     */
    User getUserBasicInfoById(@Param("id") Integer id);

    /**
     * 根据用户主键删除用户
     *
     * @param id
     * @return
     */
    boolean deleteUserById(@Param("id") Integer id);
}
