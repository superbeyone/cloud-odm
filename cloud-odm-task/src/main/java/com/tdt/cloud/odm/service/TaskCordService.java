package com.tdt.cloud.odm.service;


import com.tdt.cloud.odm.pojo.data.SearchModel;
import com.tdt.cloud.odm.pojo.task.TaskCord;

import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className TaskCordService
 * @description 任务组Service
 * @date 2019-03-21 09:19
 **/

public interface TaskCordService {

    /**
     * 添加任务组信息
     *
     * @param taskCord
     * @return
     */
    boolean addTaskCord(TaskCord taskCord);

    /**
     * 更改任务组名称
     *
     * @param id   主键id
     * @param name 任务组名称
     * @return
     */
    boolean updateTaskCordName(int id, String name);

    /**
     * 根据用户行政区划获取就绪中的任务组数量
     *
     * @param provinceCode
     * @return
     */
    int getReadyTaskCordCountByUserProvinceCode(String provinceCode);

    /**
     * 根据用户主键Id获取就绪中的任务组数量
     *
     * @param userId
     * @return
     */
    int getReadyTaskCordCountByUserId(int userId);

    /**
     * 根据用户主键Id获取就绪中的任务组
     *
     * @param userId
     * @return
     */
    TaskCord getReadyTaskCordByUserId(int userId);

    /**
     * 根据任务组主键获取任务组信息
     *
     * @param id
     * @return
     */
    TaskCord getTaskCordById(int id);

    /**
     * 根据用户行政区划获取就绪中的任务组列表
     *
     * @param provinceCode
     * @return
     */
    TaskCord getReadyTaskCordByUserProvinceCode(String provinceCode);

    /**
     * 获取所有准备中的任务组列表（超级管理员使用）
     *
     * @return
     */
    List<TaskCord> getAllReadyTaskCord();

    /**
     * 根据用户行政区划获取运行中的任务组信息
     *
     * @param provinceCode
     * @return
     */
    List<TaskCord> getRunningTaskCordByUserProvinceCode(String provinceCode);

    /**
     * 根据用户的行政区划，获取当前准备中的任务组中最大的等待编号
     *
     * @param provinceCode
     * @return
     */
    int getMaxWaitingQueueOnReadyByUserProvinceCode(String provinceCode);

    /**
     * 根据行政区划获取排队中的任务组列表
     *
     * @param provinceCode
     * @return
     */
    List<TaskCord> getWaitingQueueOnReadyByUserProvinceCode(String provinceCode);

    /**
     * 根据用户Id获取运行中的任务组信息
     *
     * @param userId
     * @return
     */
    TaskCord getRunningTaskCordByUserId(int userId);

    /**
     * 获取所有运行中的任务组列表（超级管理员使用）
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<TaskCord> getAllRunningTaskCordByPage(int pageNo, int pageSize);

    /**
     * 根据用户主键Id分页展示任务组列表
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<TaskCord> getUserTaskCordByUserIdAndPage(int userId, int pageNo, int pageSize);

    /**
     * 根据主键Id获取任务列表的数量
     *
     * @param userId
     * @return
     */
    int getUserTaskCordCountByUserId(int userId);

    /**
     * 获取所有的任务组列表（超级管理员使用）
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<TaskCord> getAllTaskCordByPage(int pageNo, int pageSize);

    /**
     * 获取所有任务组条数（超级管理员使用）
     *
     * @return
     */
    int getAllTaskCordCount();

    /**
     * 获取总的运行中任务组条数（超级管理员使用）
     *
     * @return
     */
    int getAllRunningTaskCount();

    /**
     * 根据行政区划，获取其内所有的任务组列表
     *
     * @param provinceCode
     * @return
     */
    List<TaskCord> getTaskCordListByProvinceCode(String provinceCode);

    /**
     * 根据主键删除任务组
     *
     * @param id
     * @return
     */
    boolean deleteTaskCordById(int id);

    /**
     * 根据用户的主键Id，重置该用户下的运行中的任务组状态为[手动失败]状态
     *
     * @param userId
     * @return
     */
    boolean updateRunningTaskCordStatusToHandFailByUserId(int userId);

    /**
     * 根据任务组主键更新任务组内的任务数量
     *
     * @param id
     * @return
     */
    boolean updateTaskCordOfTaskCountByTaskCordId(int id);

    /**
     * 更新准备中的任务组的等待队列值
     *
     * @param taskCord
     * @return
     */
    boolean updateOnReadyTaskCordWaitingQueue(TaskCord taskCord);

    /**
     * 获取其它用户排队等待中的任务组
     *
     * @param provinceCode
     * @return
     */
    List<TaskCord> getTaskCordListOnWaitQueueByUserProvince(String provinceCode);

    /**
     * 更新任务组的执行状态
     *
     * @param cord
     * @return
     */
    boolean updateTaskCordStatus(TaskCord cord);

    /**
     * 获取用户最后一次执行的任务组信息
     *
     * @param userId
     * @return
     */
    TaskCord getLastestTaskCordByUserId(Integer userId);

    /**
     * 根据搜索条件检索
     *
     * @param searchModel
     * @return
     */
    List<TaskCord> getSearchResult(SearchModel searchModel);

}
