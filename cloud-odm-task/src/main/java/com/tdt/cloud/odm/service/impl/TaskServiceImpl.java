package com.tdt.cloud.odm.service.impl;

import com.tdt.cloud.odm.persist.TaskMapper;
import com.tdt.cloud.odm.pojo.data.SearchModel;
import com.tdt.cloud.odm.pojo.task.Task;
import com.tdt.cloud.odm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className TaskServiceImpl
 * @description
 * @date 2019-03-21 09:18
 **/
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

    /**
     * 根据任务主键删除任务信息（支持批量操作）
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteTaskBatchByTaskIds(List<Integer> ids) {
        return taskMapper.deleteTaskBatchByTaskIds(ids);
    }

    /**
     * 根据任务组主键删除任务
     *
     * @param cordId
     * @return
     */
    @Override
    public boolean deleteTaskByTaskCordId(int cordId) {
        return taskMapper.deleteTaskByTaskCordId(cordId);
    }

    /**
     * 根据用户主键获取准备中的任务
     *
     * @param userId
     * @return
     */
    @Override
    public List<Task> getTaskOnReadyByUserId(int userId) {
        return taskMapper.getTaskOnReadyByUserId(userId);
    }

    /**
     * 根据用户主键获取不在任务组中的任务
     *
     * @param userId
     * @return
     */
    @Override
    public List<Task> getTaskListOfNotInCordByUserId(int userId) {
        return taskMapper.getTaskListOfNotInCordByUserId(userId);
    }

    /**
     * 更新任务信息
     *
     * @param task
     * @return
     */
    @Override
    public boolean updateTask(Task task) {
        return taskMapper.updateTask(task);
    }

    /**
     * 添加任务
     *
     * @param task
     * @return
     */
    @Override
    public int addTask(Task task) {
        return taskMapper.addTask(task);
    }

    /**
     * 根据主键获取任务信息
     *
     * @param id
     * @return
     */
    @Override
    public Task getTaskById(int id) {
        return taskMapper.getTaskById(id);
    }

    /**
     * 根据行政区划代码获取其内的非包含在任务组内的任务列表
     *
     * @param provinceCode
     * @return
     */
    @Override
    public List<Task> getTaskListOfNotInCordByUserProvinceCode(String provinceCode) {
        return taskMapper.getTaskListOfNotInCordByUserProvinceCode(provinceCode);
    }

    /**
     * 根据任务组主键获取其内的任务列表
     *
     * @param cordId
     * @return
     */
    @Override
    public LinkedList<Task> getTaskListOfInCordByCordId(int cordId) {
        return taskMapper.getTaskListOfInCordByCordId(cordId);
    }

    /**
     * 任务重排序
     *
     * @param ids 排序后的任务主键id列表
     * @return
     */
    @Override
    public boolean updateTaskSequence(Integer[] ids) {
        return taskMapper.updateTaskSequence(ids);
    }

    /**
     * 根据搜索条件检索
     *
     * @param searchModel
     * @return
     */
    @Override
    public List<Task> getSearchResult(SearchModel searchModel) {
        return taskMapper.getSearchResult(searchModel);
    }

    /**
     * 根据用户主键Id，查询任务
     *
     * @param userId
     * @param ids    排除队列
     * @return
     */
    @Override
    public LinkedList<Task> getTaskListOfInCordByUserId(Integer userId, List<Integer> ids) {
        return taskMapper.getTaskListOfInCordByUserId(userId, ids);
    }

    /**
     * 根据用户主键Id,更新执行中任务状态为手动失败
     *
     * @param userId
     * @return
     */
    @Override
    public boolean updateRunningTaskStatusToHandFailByUserId(Integer userId) {
        return taskMapper.updateRunningTaskStatusToHandFailByUserId(userId);
    }

    /**
     * 根据用户主键获取该用户不在任务组里的任务数量
     *
     * @param searchModel
     * @return
     */
    @Override
    public int getTaskOfNotInCordCountByCondition(SearchModel searchModel) {
        return taskMapper.getTaskOfNotInCordCountByCondition(searchModel);
    }

    /**
     * 根据用户主键获取运行中的Task
     *
     * @param userId
     * @return
     */
    @Override
    public Task getTaskOnRunningByUserId(Integer userId) {
        return taskMapper.getTaskOnRunningByUserId(userId);
    }

    /**
     * 根据用户主键获取最后一条任务信息
     *
     * @param userId
     * @return
     */
    @Override
    public Task getLastTaskByUserId(Integer userId) {
        return taskMapper.getLastTaskByUserId(userId);
    }

    /**
     * 根据行政区划获取不在任务组内的任务列表
     *
     * @param provinceCode
     * @return
     */
    @Override
    public List<Task> getTaskOnRunningOfNotInCordByUserProvince(String provinceCode) {
        return taskMapper.getTaskOnRunningOfNotInCordByUserProvince(provinceCode);
    }

    /**
     * 获取任务组内的任务数量
     *
     * @param cordId
     * @return
     */
    @Override
    public int getTaskListCountByCordId(Integer cordId) {
        return taskMapper.getTaskListCountByCordId(cordId);
    }

    /**
     * 根据任务主键id更新序号
     *
     * @param taskId
     * @param sequence
     * @return
     */
    @Override
    public int updateTaskSequenceByTaskId(Integer taskId, int sequence) {
        return taskMapper.updateTaskSequenceByTaskId(taskId, sequence);
    }
}
