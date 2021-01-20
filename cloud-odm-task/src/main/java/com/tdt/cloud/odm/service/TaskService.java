package com.tdt.cloud.odm.service;


import com.tdt.cloud.odm.pojo.data.SearchModel;
import com.tdt.cloud.odm.pojo.task.Task;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className TaskService
 * @description 任务Service
 * @date 2019-03-21 09:17
 **/

public interface TaskService {
    /**
     * 添加任务
     *
     * @param task
     * @return
     */
    int addTask(Task task);

    /**
     * 根据主键获取任务信息
     *
     * @param id
     * @return
     */
    Task getTaskById(int id);

    /**
     * 根据用户主键获取准备中的任务
     *
     * @param userId
     * @return
     */
    List<Task> getTaskOnReadyByUserId(int userId);

    /**
     * 根据用户主键获取不在任务组中的任务
     *
     * @param userId
     * @return
     */
    List<Task> getTaskListOfNotInCordByUserId(int userId);

    /**
     * 根据行政区划代码获取其内的非包含在任务组内的任务列表
     *
     * @param provinceCode
     * @return
     */
    List<Task> getTaskListOfNotInCordByUserProvinceCode(String provinceCode);

    /**
     * 根据任务组主键获取其内的任务列表
     *
     * @param cordId
     * @return
     */
    LinkedList<Task> getTaskListOfInCordByCordId(int cordId);

    /**
     * 任务重排序
     *
     * @param ids 排序后的任务主键id列表
     * @return
     */
    boolean updateTaskSequence(Integer[] ids);

    /**
     * 根据任务主键删除任务信息（支持批量操作）
     *
     * @param ids
     * @return
     */
    boolean deleteTaskBatchByTaskIds(List<Integer> ids);

    /**
     * 根据任务组主键删除任务
     *
     * @param cordId
     * @return
     */
    boolean deleteTaskByTaskCordId(int cordId);

    /**
     * 更新任务信息
     *
     * @param task
     * @return
     */
    boolean updateTask(Task task);

    /**
     * 根据搜索条件检索
     *
     * @param searchModel
     * @return
     */
    List<Task> getSearchResult(SearchModel searchModel);

    /**
     * 根据用户主键Id，查询任务
     *
     * @param userId
     * @param ids    排除队列
     * @return
     */
    LinkedList<Task> getTaskListOfInCordByUserId(Integer userId, List<Integer> ids);

    /**
     * 根据用户主键Id,更新执行中任务状态为手动失败
     *
     * @param userId
     * @return
     */
    boolean updateRunningTaskStatusToHandFailByUserId(Integer userId);

    /**
     * 根据用户主键获取该用户不在任务组里的任务数量
     *
     * @param searchModel
     * @return
     */
    int getTaskOfNotInCordCountByCondition(SearchModel searchModel);

    /**
     * 根据用户主键获取运行中的Task
     *
     * @param userId
     * @return
     */
    Task getTaskOnRunningByUserId(Integer userId);

    /**
     * 根据用户主键获取最后一条任务信息
     *
     * @param userId
     * @return
     */
    Task getLastTaskByUserId(Integer userId);

    /**
     * 根据行政区划获取不在任务组内的任务列表
     *
     * @param provinceCode
     * @return
     */
    List<Task> getTaskOnRunningOfNotInCordByUserProvince(String provinceCode);

    /**
     * 获取任务组内的任务数量
     *
     * @param cordId
     * @return
     */
    int getTaskListCountByCordId(Integer cordId);

    /**
     * 根据任务主键id更新序号
     *
     * @param taskId
     * @param sequence
     * @return
     */
    int updateTaskSequenceByTaskId(Integer taskId, int sequence);
}
