package com.tdt.cloud.odm.persist;

import com.tdt.cloud.odm.pojo.task.TaskCord;
import com.tdt.cloud.odm.pojo.data.SearchModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className TaskCordMapper
 * @description 任务组数据操作接口
 * @date 2019-03-26 16:29
 **/
@Mapper
@Repository
public interface TaskCordMapper {
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
    boolean updateTaskCordName(@Param("id") int id, @Param("name") String name);

    /**
     * 根据用户行政区划获取就绪中的任务组数量
     *
     * @param provinceCode
     * @return
     */
    int getReadyTaskCordCountByUserProvinceCode(@Param("provinceCode") String provinceCode);

    /**
     * 根据用户行政区划获取准备中的任务组列表
     *
     * @param provinceCode
     * @return
     */
    TaskCord getReadyTaskCordByUserProvinceCode(@Param("provinceCode") String provinceCode);

    /**
     * 根据用户行政区划获取运行中的任务组信息
     *
     * @param provinceCode
     * @return
     */
    List<TaskCord> getRunningTaskCordByUserProvinceCode(@Param("provinceCode") String provinceCode);

    /**
     * 获取所有运行中的任务组列表（超级管理员使用）
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<TaskCord> getAllRunningTaskCordByPage(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    /**
     * 获取所有的任务组列表（超级管理员使用）
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<TaskCord> getAllTaskCordByPage(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    /**
     * 获取所有任务组条数（超级管理员使用）
     *
     * @return
     */
    int getAllTaskCordCount();

    /**
     * 获取总运行中任务组条数（超级管理员使用）
     *
     * @return
     */
    int getAllRunningTaskCount();

    /**
     * 获取所有准备中的任务组列表信息（超级管理员使用）
     *
     * @return
     */
    List<TaskCord> getAllReadyTaskCord();


    /**
     * 根据行政区划，获取其内所有的任务组列表
     *
     * @param provinceCode
     * @return
     */
    List<TaskCord> getTaskCordListByProvinceCode(@Param("provinceCode") String provinceCode);

    /**
     * 根据用户主键Id获取就绪中的任务组数量
     *
     * @param userId
     * @return
     */
    int getReadyTaskCordCountByUserId(@Param("userId") int userId);

    /**
     * 根据用户主键Id获取就绪中的任务组
     *
     * @param userId
     * @return
     */
    TaskCord getReadyTaskCordByUserId(@Param("userId") int userId);

    /**
     * 根据任务组主键获取任务组信息
     *
     * @param id
     * @return
     */
    TaskCord getTaskCordById(@Param("id") int id);

    /**
     * 根据主键删除任务组
     *
     * @param id
     * @return
     */
    boolean deleteTaskCordById(@Param("id") int id);

    /**
     * 根据用户的主键Id，重置该用户下的运行中的任务组状态为[手动失败]状态
     *
     * @param userId
     * @return
     */
    boolean updateRunningTaskCordStatusToHandFailByUserId(@Param("userId") int userId);

    /**
     * 根据任务组主键Id更新该任务组内的任务数量
     *
     * @param id
     * @return
     */
    boolean updateTaskCordOfTaskCountByTaskCordId(@Param("id") int id);

    /**
     * 根据用户Id获取运行中的任务组信息
     *
     * @param userId
     * @return
     */
    TaskCord getRunningTaskCordByUserId(@Param("userId") int userId);

    /**
     * 根据用户主键Id分页展示任务组列表
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<TaskCord> getUserTaskCordByUserIdAndPage(@Param("userId") int userId, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    /**
     * 根据主键Id获取任务列表的数量
     *
     * @param userId
     * @return
     */
    int getUserTaskCordCountByUserId(@Param("userId") int userId);

    /**
     * 根据用户的行政区划，获取当前准备中的任务组中最大的等待编号
     *
     * @param provinceCode
     * @return
     */
    int getMaxWaitingQueueOnReadyByUserProvinceCode(@Param("provinceCode") String provinceCode);

    /**
     * 更新准备中的任务组的等待队列值
     *
     * @param taskCord
     * @return
     */
    boolean updateOnReadyTaskCordWaitingQueue(@Param("taskCord") TaskCord taskCord);

    /**
     * 获取其它用户排队等待中的任务组
     *
     * @param provinceCode
     * @return
     */
    List<TaskCord> getTaskCordListOnWaitQueueByUserProvince(@Param("provinceCode") String provinceCode);

    /**
     * 更新任务组的执行状态
     *
     * @param taskCord
     * @return
     */
    boolean updateTaskCordStatus(@Param("taskCord") TaskCord taskCord);

    /**
     * 获取用户最后一次执行的任务组信息
     *
     * @param userId
     * @return
     */
    TaskCord getLastestTaskCordByUserId(@Param("userId") Integer userId);

    /**
     * 根据搜索条件检索
     *
     * @param searchModel
     * @return
     */
    List<TaskCord> getSearchResult(SearchModel searchModel);

    /**
     * 根据行政区划获取排队中的任务组列表
     *
     * @param provinceCode
     * @return
     */
    List<TaskCord> getWaitingQueueOnReadyByUserProvinceCode(@Param("provinceCode") String provinceCode);
}
