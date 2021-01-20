package com.tdt.cloud.odm.service.impl;

import com.tdt.cloud.odm.persist.TaskCordMapper;
import com.tdt.cloud.odm.pojo.data.SearchModel;
import com.tdt.cloud.odm.pojo.task.TaskCord;
import com.tdt.cloud.odm.service.TaskCordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className TaskCordServiceImpl
 * @description
 * @date 2019-03-21 09:20
 **/
@Service
public class TaskCordServiceImpl implements TaskCordService {


    @Autowired
    TaskCordMapper taskCordMapper;

    /**
     * 根据任务组主键更新任务组内的任务数量
     *
     * @param id
     * @return
     */
    @Override
    public boolean updateTaskCordOfTaskCountByTaskCordId(int id) {
        return taskCordMapper.updateTaskCordOfTaskCountByTaskCordId(id);
    }

    /**
     * 根据用户的主键Id，重置该用户下的运行中的任务组状态为[手动失败]状态
     *
     * @param userId
     * @return
     */
    @Override
    public boolean updateRunningTaskCordStatusToHandFailByUserId(int userId) {
        return taskCordMapper.updateRunningTaskCordStatusToHandFailByUserId(userId);
    }

    /**
     * 根据主键删除任务组
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteTaskCordById(int id) {
        return taskCordMapper.deleteTaskCordById(id);
    }

    /**
     * 添加任务组信息
     *
     * @param taskCord
     * @return
     */
    @Override
    public boolean addTaskCord(TaskCord taskCord) {
        return taskCordMapper.addTaskCord(taskCord);
    }

    /**
     * 更改任务组名称
     *
     * @param id   主键id
     * @param name 任务组名称
     * @return
     */
    @Override
    public boolean updateTaskCordName(int id, String name) {
        return taskCordMapper.updateTaskCordName(id, name);
    }

    /**
     * 根据用户行政区划获取就绪中的任务组数量
     *
     * @param provinceCode
     * @return
     */
    @Override
    public int getReadyTaskCordCountByUserProvinceCode(String provinceCode) {
        return taskCordMapper.getReadyTaskCordCountByUserProvinceCode(provinceCode);
    }

    /**
     * 根据用户主键Id获取就绪中的任务组数量
     *
     * @param userId
     * @return
     */
    @Override
    public int getReadyTaskCordCountByUserId(int userId) {
        return taskCordMapper.getReadyTaskCordCountByUserId(userId);
    }

    /**
     * 根据用户主键Id获取就绪中的任务组
     *
     * @param userId
     * @return
     */
    @Override
    public TaskCord getReadyTaskCordByUserId(int userId) {
        return taskCordMapper.getReadyTaskCordByUserId(userId);
    }

    /**
     * 根据任务组主键获取任务组信息
     *
     * @param id
     * @return
     */
    @Override
    public TaskCord getTaskCordById(int id) {
        return taskCordMapper.getTaskCordById(id);
    }

    /**
     * 根据用户行政区划获取准备中的任务组列表
     *
     * @param provinceCode
     * @return
     */
    @Override
    public TaskCord getReadyTaskCordByUserProvinceCode(String provinceCode) {
        return taskCordMapper.getReadyTaskCordByUserProvinceCode(provinceCode);
    }

    /**
     * 获取所有准备中的任务组列表（超级管理员使用）
     *
     * @return
     */
    @Override
    public List<TaskCord> getAllReadyTaskCord() {
        return taskCordMapper.getAllReadyTaskCord();
    }

    /**
     * 根据用户行政区划获取运行中的任务组信息
     *
     * @param provinceCode
     * @return
     */
    @Override
    public List<TaskCord> getRunningTaskCordByUserProvinceCode(String provinceCode) {
        return taskCordMapper.getRunningTaskCordByUserProvinceCode(provinceCode);
    }

    /**
     * 根据用户Id获取运行中的任务组信息
     *
     * @param userId
     * @return
     */
    @Override
    public TaskCord getRunningTaskCordByUserId(int userId) {
        return taskCordMapper.getRunningTaskCordByUserId(userId);
    }

    /**
     * 获取所有运行中的任务组列表（超级管理员使用）
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<TaskCord> getAllRunningTaskCordByPage(int pageNo, int pageSize) {
        return taskCordMapper.getAllRunningTaskCordByPage((pageNo - 1) * pageSize, pageSize);
    }

    /**
     * 根据用户主键Id分页展示任务组列表
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<TaskCord> getUserTaskCordByUserIdAndPage(int userId, int pageNo, int pageSize) {
        return taskCordMapper.getUserTaskCordByUserIdAndPage(userId, (pageNo - 1) * pageSize, pageSize);
    }

    /**
     * 根据主键Id获取任务列表的数量
     *
     * @param userId
     * @return
     */
    @Override
    public int getUserTaskCordCountByUserId(int userId) {
        return taskCordMapper.getUserTaskCordCountByUserId(userId);
    }

    /**
     * 获取所有的任务组列表（超级管理员使用）
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public List<TaskCord> getAllTaskCordByPage(int pageNo, int pageSize) {
        return taskCordMapper.getAllTaskCordByPage((pageNo - 1) * pageSize, pageSize);
    }

    /**
     * 获取所有任务组条数（超级管理员使用）
     *
     * @return
     */
    @Override
    public int getAllTaskCordCount() {
        return taskCordMapper.getAllTaskCordCount();
    }

    /**
     * 获取总运行中任务组条数（超级管理员使用）
     *
     * @return
     */
    @Override
    public int getAllRunningTaskCount() {
        return taskCordMapper.getAllRunningTaskCount();
    }

    /**
     * 根据行政区划，获取其内所有的任务组列表
     *
     * @param provinceCode
     * @return
     */
    @Override
    public List<TaskCord> getTaskCordListByProvinceCode(String provinceCode) {
        return taskCordMapper.getTaskCordListByProvinceCode(provinceCode);
    }

    /**
     * 根据用户的行政区划，获取当前准备中的任务组中最大的等待编号
     *
     * @param provinceCode
     * @return
     */
    @Override
    public int getMaxWaitingQueueOnReadyByUserProvinceCode(String provinceCode) {
        return taskCordMapper.getMaxWaitingQueueOnReadyByUserProvinceCode(provinceCode);
    }

    /**
     * 根据行政区划获取排队中的任务组列表
     *
     * @param provinceCode
     * @return
     */
    @Override
    public List<TaskCord> getWaitingQueueOnReadyByUserProvinceCode(String provinceCode) {
        return taskCordMapper.getWaitingQueueOnReadyByUserProvinceCode(provinceCode);
    }

    /**
     * 更新准备中的任务组的等待队列值
     *
     * @param taskCord
     */
    @Override
    public boolean updateOnReadyTaskCordWaitingQueue(TaskCord taskCord) {
        return taskCordMapper.updateOnReadyTaskCordWaitingQueue(taskCord);
    }

    /**
     * 获取其它用户排队等待中的任务组
     *
     * @param provinceCode
     * @return
     */
    @Override
    public List<TaskCord> getTaskCordListOnWaitQueueByUserProvince(String provinceCode) {
        return taskCordMapper.getTaskCordListOnWaitQueueByUserProvince(provinceCode);
    }

    /**
     * 更新任务组的执行状态
     *
     * @param taskCord
     * @return
     */
    @Override
    public boolean updateTaskCordStatus(TaskCord taskCord) {
        return taskCordMapper.updateTaskCordStatus(taskCord);
    }

    /**
     * 获取用户最后一次执行的任务组信息
     *
     * @param userId
     * @return
     */
    @Override
    public TaskCord getLastestTaskCordByUserId(Integer userId) {
        return taskCordMapper.getLastestTaskCordByUserId(userId);
    }

    /**
     * 根据搜索条件检索
     *
     * @param searchModel
     * @return
     */
    @Override
    public List<TaskCord> getSearchResult(SearchModel searchModel) {
        return taskCordMapper.getSearchResult(searchModel);
    }
}
