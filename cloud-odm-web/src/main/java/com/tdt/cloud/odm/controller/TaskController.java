package com.tdt.cloud.odm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tdt.cloud.commons.executor.TdtExecutor;
import com.tdt.cloud.commons.result.JsonResult;
import com.tdt.cloud.commons.result.ResultCodeEnum;
import com.tdt.cloud.commons.result.TdtStaticConst;
import com.tdt.cloud.odm.exception.CommonException;
import com.tdt.cloud.odm.pojo.OperateLogVo;
import com.tdt.cloud.odm.pojo.data.SearchModel;
import com.tdt.cloud.odm.pojo.mongo.MongoAddr;
import com.tdt.cloud.odm.pojo.mongo.MongoDB;
import com.tdt.cloud.odm.pojo.task.Task;
import com.tdt.cloud.odm.pojo.task.TaskCord;
import com.tdt.cloud.odm.pojo.task.TaskDto;
import com.tdt.cloud.odm.pojo.task.TaskVo;
import com.tdt.cloud.odm.pojo.user.User;
import com.tdt.cloud.odm.service.MongoConnectionService;
import com.tdt.cloud.odm.service.MongoDBService;
import com.tdt.cloud.odm.service.TaskCordService;
import com.tdt.cloud.odm.service.TaskService;
import com.tdt.cloud.utils.TaskUtil;
import com.tdt.cloud.utils.range.CalcCountFromRangeModelUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className TaskController
 * @description 任务Controller
 * @date 2019-03-26 15:47
 **/
@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    MongoConnectionService mongoConnectionService;

    @Autowired
    MongoDBService mongoDBService;

    @Autowired
    TaskCordService taskCordService;

    @Resource
    TdtExecutor tdtExecutor;

    private Logger logger = LoggerFactory.getLogger(getClass());


    private Long start = null;
    private Long end = null;

    /**
     * 添加单任务，并执行
     *
     * @param task
     * @param request
     * @return
     */
    @PostMapping("/task/exec")
    public JsonResult<Void> addTask(Task task, HttpServletRequest request) {
        if (StringUtils.isBlank(task.getParamVal())) {
            throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL);
        }
        if (StringUtils.endsWith(task.getOperateType(), TdtStaticConst.OPT_TYPE_OF_EXPORT) && StringUtils.isBlank(task.getSavePath())) {
            throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL);
        }
        Object u = request.getSession().getAttribute(TdtStaticConst.USER_SESSION_KEY);
        if (u == null) {
            throw new CommonException(ResultCodeEnum.NO_USER_INFO);
        }
        Map<String, String> map = new ConcurrentHashMap<>();
        if (task.getDbId() == null) {
            if (!StringUtils.endsWith(task.getOperateType(), TdtStaticConst.OPT_TYPE_OF_EXPORT)) {
                throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getCode(), "请选择要操作的数据库");
            }
        } else {
            MongoDB mongoDB = mongoDBService.getMongoDBById(task.getDbId());
            task.setDbName(mongoDB.getName());
        }
        map.put(task.getDbId() + "", task.getDbName());
        User user = (User) u;
//        MongoAddr mongoAddr = mongoConnectionService.getActivateMongoConnectionByUserProvinceCode(user.getProvinceCode());

        //从Session中获取Mongo连接
        Object mongoAddrObj = request.getSession().getAttribute(TdtStaticConst.MONGO_CONN_SESSION_KEY);
        if (mongoAddrObj == null) {
            throw new CommonException(ResultCodeEnum.MUST_ACTIVATE_MONGO_CONN_FIRST);
        }
        MongoAddr mongoAddr = (MongoAddr) mongoAddrObj;
        //当前用户有正在运行中的组，阻止
        TaskCord runningTaskCord = taskCordService.getRunningTaskCordByUserId(user.getId());
        if (runningTaskCord != null) {
            throw new CommonException(ResultCodeEnum.EXIST_OTHER_RUNNING_TASK_OF_SAME_USER);
        }
        if (task.getDbId() != null) {
            List<Task> tasks = taskService.getTaskListOfNotInCordByUserProvinceCode(user.getProvinceCode());
            long count = tasks.stream().filter(t ->
                    (t.getStatus() == 1) && (task.getDbId().equals(t.getDbId()))
            ).count();
            if (count > 0) {
                throw new CommonException(ResultCodeEnum.EXIST_OTHER_RUNNING_TASK_ON_SAME_DATABASE.getCode(),
                        ResultCodeEnum.EXIST_OTHER_RUNNING_TASK_ON_SAME_DATABASE.getMsg() + "<br/>可选择创建任务组，并将此任务添加入等待队列...");
            }
        }


        /**
         * 根据行政区划获取所有正在运行中的任务
         */
        List<Task> list = taskService.getTaskOnRunningOfNotInCordByUserProvince(user.getProvinceCode());
        if (list.size() > 0) {
            for (Task task1 : list) {
                //判断具体的库操作是不是操作的同一个库
                if (!StringUtils.equals(task.getOperateType(), TdtStaticConst.OPT_TYPE_OF_EXPORT) && map.get(task1.getDbId()) != null) {
                    throw new CommonException(ResultCodeEnum.EXIST_OTHER_RUNNING_TASK_ON_SAME_DATABASE.getCode(), ResultCodeEnum.EXIST_OTHER_RUNNING_TASK_ON_SAME_DATABASE.getMsg() + "<br>您可选择排队等待操作");
                }
            }
        }
        //根据行政区划获取所有运行中的任务组
        List<TaskCord> taskCords = taskCordService.getRunningTaskCordByUserProvinceCode(user.getProvinceCode());
        if (taskCords.size() > 0) {
            for (TaskCord cord : taskCords) {
                LinkedList<Task> tasks = taskService.getTaskListOfInCordByCordId(cord.getId());
                if (tasks.size() > 0) {
                    for (Task task2 : tasks) {
                        //判断具体的库操作是不是操作的同一个库

                        if (map.get(task2.getDbId()) != null) {
                            if (!StringUtils.equals(task2.getOperateType(), TdtStaticConst.OPT_TYPE_OF_EXPORT)) {
                                throw new CommonException(ResultCodeEnum.EXIST_OTHER_RUNNING_TASK_ON_SAME_DATABASE.getCode(), ResultCodeEnum.EXIST_OTHER_RUNNING_TASK_ON_SAME_DATABASE.getMsg() + "<br>您可选择排队等待操作");
                            }
                        }
                    }
                }
            }
        }

        long size = -1;
        if (StringUtils.endsWithIgnoreCase(task.getParamVal(), ".xls") || StringUtils.endsWithIgnoreCase(task.getParamVal(), ".xlsx")) {
            if (StringUtils.startsWithIgnoreCase(task.getParamVal(), TdtStaticConst.SMB_PREFIX)) {
                throw new CommonException(500, "暂不支持远程操作");
            } else {

                size = TaskUtil.calcExcelExportExecTime(new File(task.getParamVal())) * 20;
            }
            if (size < 0) {
                throw new CommonException(ResultCodeEnum.FAIL.getCode(), "Excel文件数据不合法");
            }
        }

        end = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        if (start != null) {
            if ((end - start) < 5) {
                throw new CommonException(5003, "手速太快了，请等待<span style='color:red'>&nbsp;" + (5 - (end - start)) + "&nbsp;</span>秒后重试...");
            }
        }
        start = end;
        task.setOperatorName(user.getUsername());
        task.setCreateTime(new Date());
        task.setProvinceCode(user.getProvinceCode());
        task.setConnId(mongoAddr.getId());
        task.setSerialNum(TaskUtil.generateTaskSerialNum(user.getId()));
        task.setStatus(0);
        task.setUserId(user.getId());
        task.setDbName(StringUtils.trim(task.getDbName()));
        task.setThreadCount(Math.min(task.getThreadCount(), 30));

        if (StringUtils.isNotBlank(task.getSavePath())) {
            File savePath = new File(task.getSavePath());
            if (!savePath.exists()) {
                savePath.mkdirs();
            }
        }
        if (StringUtils.isNotBlank(task.getPackPath())) {
            File packPath = new File(task.getPackPath());
            if (!packPath.exists()) {
                packPath.mkdirs();
            }
        }
        if (size < 0) {
            if (StringUtils.startsWithIgnoreCase(task.getParamVal(), TdtStaticConst.SMB_PREFIX)) {
                throw new CommonException(500, "暂不支持远程操作");
            } else {
                size = getCountSize(task, user, size);
            }
        }
        if (size < 0) {
            throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getCode(), "重要参数数据不合法");
        }
        setPredictTime(task, size);
        task.setPredictTime(task.getPredictTime() < 1 ? 1 : task.getPredictTime());
        int execCount = taskService.addTask(task);
        if (task.getCordId() != null) {
            int count = taskService.getTaskListCountByCordId(task.getCordId());
            task.setSequence(count + 1);
            taskService.updateTaskSequenceByTaskId(task.getId(), count + 1);
        }
        TdtStaticConst.putMapFlag(user.getUsername(), user.getId(), false);
        if (execCount > 0) {
            //在此处异步执行该任务
            String threadName = TdtStaticConst.RUNNING_TASK_CORD_NAME + "_" + user.getUsername() + "_" + user.getId();
            logger.debug("异步执行单任务:\t{}", task);
            //开启线程异步执行任务
//            new AsyncExecTaskThread(task, user, threadName, taskService, dataOperateService, executor).start();
//            new AsyncExecTaskThreadExt(task, user, threadName, taskService, executor,
//                    dataImportService, dataDeleteService, dataExportService, dataCheckService, dataMigrateService).start();
            return JsonResult.success("启动执行成功");

        }
        return JsonResult.fail();
    }

    /**
     * 添加任务数据到任务表以及任务组表
     *
     * @param task
     * @param request
     * @return
     */
    @PostMapping("/task/cord")
    public JsonResult<Task> addTaskAndCord(Task task, HttpServletRequest request) {
        task = checkTask(task, request);

        int taskId = taskService.addTask(task);
        //更新任务组内的任务数量
        taskCordService.updateTaskCordOfTaskCountByTaskCordId(task.getCordId());

        if (task.getCordId() != null) {
            int count = taskService.getTaskListCountByCordId(task.getCordId());
            task.setSequence(count + 1);
            taskService.updateTaskSequenceByTaskId(task.getId(), count + 1);
        }
        if (taskId > 0) {
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

    /**
     * 校验任务信息
     *
     * @param task    task
     * @param request req
     * @return 结果
     */
    private Task checkTask(Task task, HttpServletRequest request) {
        if (StringUtils.isBlank(task.getParamVal())) {
            throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getCode(), ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getMsg());
        }
        if (StringUtils.endsWith(task.getOperateType(), TdtStaticConst.OPT_TYPE_OF_EXPORT) && StringUtils.isBlank(task.getSavePath())) {
            throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getCode(), ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getMsg());
        }
        Object u = request.getSession().getAttribute(TdtStaticConst.USER_SESSION_KEY);
        if (u == null) {
            throw new CommonException(ResultCodeEnum.NO_USER_INFO.getCode(), ResultCodeEnum.NO_USER_INFO.getMsg());
        }

        if (!StringUtils.endsWith(task.getOperateType(), TdtStaticConst.OPT_TYPE_OF_EXPORT)) {
            if (task.getDbId() == null) {
                throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getCode(), "请选择要操作的数据库");
            }
        }
        User user = (User) u;
        end = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        if (start != null) {
            if ((end - start) < 3) {
                throw new CommonException(5003, "手速太快了，请等待<span style='color:red'>&nbsp;" + (3 - (end - start)) + "&nbsp;</span>秒后重试...");
            }
        }

        start = end;
        TaskCord taskCord = null;
        taskCord = taskCordService.getReadyTaskCordByUserId(user.getId());
        if (taskCord == null) {
            throw new CommonException(ResultCodeEnum.NO_CAN_RUN_TASK_CORD.getCode(), ResultCodeEnum.NO_CAN_RUN_TASK_CORD.getMsg());
        } else {
            task.setCordId(taskCord.getId());
        }

        //从Session中获取Mongo连接
        Object mongoAddrObj = request.getSession().getAttribute(TdtStaticConst.MONGO_CONN_SESSION_KEY);
        if (mongoAddrObj == null) {
            throw new CommonException(ResultCodeEnum.MUST_ACTIVATE_MONGO_CONN_FIRST.getCode(), ResultCodeEnum.MUST_ACTIVATE_MONGO_CONN_FIRST.getMsg());
        }
        long size = -1;
        if (StringUtils.equals(task.getOperateType(), TdtStaticConst.OPT_TYPE_OF_EXPORT)) {
            if (StringUtils.endsWithIgnoreCase(task.getParamVal(), ".xls") || StringUtils.endsWithIgnoreCase(task.getParamVal(), ".xlsx")) {
                size = TaskUtil.calcExcelExportExecTime(new File(task.getParamVal())) * 20;
                if (size < 0) {
                    throw new CommonException(ResultCodeEnum.FAIL.getCode(), "Excel文件数据不合法");
                }
            }
        }
        MongoAddr mongoAddr = (MongoAddr) mongoAddrObj;
        task.setUserId(user.getId());
        task.setOperatorName(user.getUsername());
        task.setCreateTime(new Date());
        task.setProvinceCode(user.getProvinceCode());
        task.setConnId(mongoAddr.getId());
        task.setSerialNum(TaskUtil.generateTaskSerialNum(user.getId()));
        task.setStatus(0);
        task.setDbName(StringUtils.trim(task.getId() != null ? mongoDBService.getMongoDBById(task.getId()).getName() : task.getDbName()));
        task.setThreadCount(Math.min(task.getThreadCount(), 30));

        if (StringUtils.isNotBlank(task.getSavePath())) {
            File savePath = new File(task.getSavePath());
            if (!savePath.exists()) {
                savePath.mkdirs();
            }
        }
        if (StringUtils.isNotBlank(task.getPackPath())) {
            File packPath = new File(task.getPackPath());
            if (!packPath.exists()) {
                packPath.mkdirs();
            }
        }

        if (StringUtils.startsWithIgnoreCase(task.getParamVal(), TdtStaticConst.SMB_PREFIX)) {

//            size = SmbjFileUtil.getDirectoryOrFileSize(task.getParamVal());
            throw new CommonException(500, "暂不支持远程操作");
        } else {
            size = getCountSize(task, user, size);
        }
        if (size < 0) {
            throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getCode(), "重要参数数据不合法");
        }
        setPredictTime(task, size);
        logger.debug("添加任务：\t{}", task.toString());
        task.setPredictTime(Math.max(task.getPredictTime(), 1));
        return task;
    }

    private long getCountSize(Task task, User user, long size) {

        if (TdtStaticConst.OPT_TYPE_OF_EXPORT.equals(task.getOperateType())
                && StringUtils.startsWith(task.getParamVal(), "{")
                && StringUtils.endsWith(task.getParamVal(), "}")) {

            size = CalcCountFromRangeModelUtil.calcExecCount(task.getParamVal(), task.getDbName());

        } else {
            switch (task.getOperateType()) {
                case TdtStaticConst.OPT_TYPE_OF_CHECK:
                case TdtStaticConst.OPT_TYPE_OF_MIGRATE:
                    JSONObject jsonObject = JSON.parseObject(task.getParamVal());
                    String shpFile = jsonObject.getString("file");
                    size = getShpFileSize(user, size, shpFile);
                    break;
                default:
                    String shpFileStr = task.getParamVal();
                    size = getShpFileSize(user, size, shpFileStr);
            }
        }
        return size;
    }

    private long getShpFileSize(User user, long size, String shpFileStr) {
        File paramVal = new File(shpFileStr);
        if (paramVal.exists()) {
            if (paramVal.isDirectory()) {
                ExecutorService executor = tdtExecutor.getExecutor(user.getUsername(), 1);
                Future<Long> future = executor.submit(() -> {
                    return FileUtils.sizeOfDirectory(paramVal);
                });
                try {
                    size = future.get(2, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                    size = 2000000;
                    logger.error("异步计算文件夹大小超时，人为取值[ {} ]", size);
                }
            } else {
                size = paramVal.length();
            }
        }
        return size;
    }

    private void setPredictTime(Task task, long size) {
        switch (task.getOperateType()) {
            case TdtStaticConst.OPT_TYPE_OF_IMPORT:
                task.setPredictTime(size / TdtStaticConst.PRE_SECOND_EXEC_COUNT_OF_IMPORT / task.getThreadCount());
                break;
            case TdtStaticConst.OPT_TYPE_OF_UPDATE:
                task.setPredictTime(size / TdtStaticConst.PRE_SECOND_EXEC_COUNT_OF_UPDATE / task.getThreadCount());
                break;
            case TdtStaticConst.OPT_TYPE_OF_DELETE:
                task.setPredictTime(size / TdtStaticConst.PRE_SECOND_EXEC_COUNT_OF_DELETE / task.getThreadCount());
                break;
            case TdtStaticConst.OPT_TYPE_OF_EXPORT:
                task.setPredictTime(size / TdtStaticConst.PRE_SECOND_EXEC_COUNT_OF_EXPORT / task.getThreadCount());
                break;
            case TdtStaticConst.OPT_TYPE_OF_CHECK:
                task.setPredictTime(size / TdtStaticConst.PRE_SECOND_EXEC_COUNT_OF_CHECK / task.getThreadCount());
                break;
            case TdtStaticConst.OPT_TYPE_OF_MIGRATE:
                task.setPredictTime(size / TdtStaticConst.PRE_SECOND_EXEC_COUNT_OF_MIGRATE / task.getThreadCount());
                break;
            default:
        }
    }


    /**
     * 根据主键获取任务信息
     *
     * @param id
     * @return
     */
    @GetMapping("/task")
    public JsonResult<TaskDto> getTaskById(@RequestParam(name = "id") int id) {
        if (StringUtils.isBlank(String.valueOf(id))) {
            throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL);
        }
        Task task = taskService.getTaskById(id);
        logger.debug("根据主键ID[ {} ]获取任务信息 {}", id, task);
        TaskDto taskDto = new TaskDto();
        BeanUtils.copyProperties(task, taskDto);
        if (task != null) {
            Integer connId = task.getConnId();
            MongoAddr mongoAddr = mongoConnectionService.getMongoConnectionAddrById(connId);
            if (mongoAddr != null) {
                taskDto.setConnName(mongoAddr.getConnName());
                taskDto.setIp(mongoAddr.getIp());
                taskDto.setPort(mongoAddr.getPort());
            }
        }
        return JsonResult.getInstance().ok(taskDto);
    }

    /**
     * 根据任务主键删除任务信息（支持批量操作）
     *
     * @param
     * @return
     */
    @DeleteMapping("/task")
    public JsonResult<Void> deleteTaskBatchByTaskIds(@RequestBody Map<String, Object> map) {
        Object ids = map.get("ids");

        Object cordId = map.get("cordId");
        if (ids == null) {
            throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getCode(), ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getMsg());
        }
        boolean result = taskService.deleteTaskBatchByTaskIds((List<Integer>) ids);
        /**
         * 更新任务组的任务数量
         */
        if (cordId != null) {
            taskCordService.updateTaskCordOfTaskCountByTaskCordId((Integer) cordId);
        }
        if (result) {
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

    /**
     * 任务重排序
     *
     * @param map 排序后的任务主键id列表
     * @return
     */
    @PutMapping("/task/sequence")
    public JsonResult<Void> updateTaskSequence(@RequestBody Map<String, List<Integer>> map) {
        List<Integer> ids = map.get("ids");
        if (StringUtils.isBlank(String.valueOf(ids))) {
            throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getCode(), ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getMsg());
        }
        Integer[] integers = new Integer[ids.size()];
        Integer[] array = ids.toArray(integers);
        boolean result = taskService.updateTaskSequence(array);
        if (result) {
            return JsonResult.success();
        }
        return JsonResult.fail();
    }


    /**
     * 手动设置名下任务为失败
     *
     * @param request req
     * @return 结果
     */
    @PutMapping("/task/fail")
    public JsonResult<Void> updateTaskStatus2Fail(HttpServletRequest request) {
        Object u = request.getSession().getAttribute(TdtStaticConst.USER_SESSION_KEY);
        if (u == null) {
            throw new CommonException(ResultCodeEnum.NO_USER_INFO.getCode(), ResultCodeEnum.NO_USER_INFO.getMsg());
        }
        User user = (User) u;
        Integer userId = user.getId();
        taskCordService.updateRunningTaskCordStatusToHandFailByUserId(userId);
        boolean result = taskService.updateRunningTaskStatusToHandFailByUserId(userId);
        if (result) {
            return JsonResult.success(user.getUsername() + " 名下运行中的任务已全部设置为手动失败");
        }
        return JsonResult.fail(500, user.getUsername() + " 名下无运行中的任务信息");
    }

    /**
     * 根据任务组主键获取其内的任务列表
     *
     * @param cordId
     * @return
     */
    @GetMapping("/task/cord")
    public JsonResult<List<Task>> getTaskListOfInCordByCordId(@RequestParam(name = "cordId") int cordId) {
        if (StringUtils.isBlank(String.valueOf(cordId))) {
            throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getCode(), ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getMsg());
        }
        List<Task> taskList = taskService.getTaskListOfInCordByCordId(cordId);
        logger.debug("根据任务组主键[ {} ]获取其内的任务列表,数量[ {} ]", cordId, taskList.size());
        return JsonResult.getInstance().ok(taskList);
    }

    /**
     * 根据行政区划获取其所有的任务组列表
     *
     * @param provinceCode
     * @return
     */
    @GetMapping("/taskCord/province")
    public JsonResult<List<TaskCord>> getTaskCordListByProvinceCode(String provinceCode) {
        if (StringUtils.isBlank(String.valueOf(provinceCode))) {
            throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getCode(), ResultCodeEnum.PARAM_CAN_NOT_BE_NULL.getMsg());
        }
        List<TaskCord> cordList = taskCordService.getTaskCordListByProvinceCode(provinceCode);
        logger.debug("根据行政区划代码[ {} ]获取其所有的任务组列表,数量[ {} ]", provinceCode, cordList.size());
        return JsonResult.getInstance().ok(cordList);
    }

    /**
     * 根据行政区划代码获取其内的非包含在任务组内的任务列表
     *
     * @param provinceCode
     * @return
     */
    @GetMapping("/task/notInCord")
    public JsonResult<List<Task>> getTaskListNotInTaskCordByProvinceCode(String provinceCode) {
        if (StringUtils.isBlank(String.valueOf(provinceCode))) {
            throw new CommonException(ResultCodeEnum.PARAM_CAN_NOT_BE_NULL);
        }
        List<Task> taskList = taskService.getTaskListOfNotInCordByUserProvinceCode(provinceCode);
        logger.debug("根据行政区划代码[ {} ]获取其内的非包含在任务组内的任务列表,数量[ {} ]", provinceCode, taskList.size());
        return JsonResult.getInstance().ok(taskList);
    }

    /**
     * 根据用户主键获取不在任务组中的任务
     *
     * @return
     */
    @GetMapping("/task/mine/notInCord")
    public JsonResult<List<Task>> getTaskListNotInTaskCordOfMine(HttpServletRequest request) {
        Object u = request.getSession().getAttribute(TdtStaticConst.USER_SESSION_KEY);
        if (u == null) {
            throw new CommonException(ResultCodeEnum.NO_USER_INFO.getCode(), ResultCodeEnum.NO_USER_INFO.getMsg());
        }
        User user = (User) u;
        return JsonResult.getInstance().ok(taskService.getTaskListOfNotInCordByUserId(user.getId()));
    }

    /**
     * 多条件查询搜索
     *
     * @param searchModel
     * @return
     */
    @GetMapping("/task/search")
    public JsonResult<List<TaskVo>> getTaskBySearch(SearchModel searchModel, HttpServletRequest request,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        logger.debug("多条件查询搜索条件\t{}", searchModel);
        JsonResult<List<TaskVo>> result = getTaskBySearchCommon(searchModel, request);
//        List<TaskVo> data = (List<TaskVo>) result.getData();
//        if (data instanceof List) {
        pageNo = pageNo < 1 ? 1 : pageNo;
        List<TaskVo> collect = (result.getData()).stream().skip((pageNo - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        return JsonResult.getInstance().ok(collect);
//        }
//        return new JsonResult<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg());
    }

    /**
     * 多条件查询搜索
     *
     * @param searchModel
     * @return
     */
    @GetMapping("/task/search/count")
    public JsonResult<Integer> getTaskBySearchCount(SearchModel searchModel, HttpServletRequest request) {
        logger.debug("多条件查询搜索条件\t{}", searchModel);
        JsonResult<List<TaskVo>> result = getTaskBySearchCommon(searchModel, request);
        Object data = result.getData();
        if (data instanceof List) {
            int size = ((List) data).size();
            logger.debug("多条件查询搜索,结果数量[ {} ]", size);
            return JsonResult.getInstance().ok(size);
        }
        return JsonResult.getInstance().ok(0);
    }


    private JsonResult<List<TaskVo>> getTaskBySearchCommon(SearchModel searchModel, HttpServletRequest request) {
        Object u = request.getSession().getAttribute(TdtStaticConst.USER_SESSION_KEY);
        if (u == null) {
            throw new CommonException(ResultCodeEnum.NO_USER_INFO);
        }
        User user = (User) u;
        if (!checkUserSuperAdminAuth(user)) {
            searchModel.setUserId(user.getId());
            searchModel.setUsername(user.getUsername());
        }
        //任务组不为空
        if (StringUtils.isNotBlank(searchModel.getCordName())) {
            List<TaskCord> taskCords = taskCordService.getSearchResult(searchModel);

//            LinkedList<HashMap<String, Object>> maps = new LinkedList<>();
            ArrayList<TaskVo> taskVos = new ArrayList<>();
            if (taskCords.size() > 0) {
                taskCords.stream().forEach(taskCord -> {
                    TaskVo taskVo;
//                    HashMap<String, Object> map = new HashMap<>();
                    LinkedList<Task> tasks = taskService.getTaskListOfInCordByCordId(taskCord.getId());
                    if (tasks.size() > 0) {
                        for (Task task : tasks) {
                            taskVo = new TaskVo();
                            BeanUtils.copyProperties(task, taskVo);
                            taskVo.setCordName(taskCord.getName());
                            taskVo.setCordRemark(taskCord.getRemark());
                            taskVos.add(taskVo);
                        }
                    }

//                    map.put("cord", taskCord);
//                    map.put("tasks", tasks);
//                    maps.add(map);
                });
                return JsonResult.getInstance().ok(taskVos);
            }
        } else {
            if (StringUtils.isNotBlank(searchModel.getSerialNum())
                    && StringUtils.isNotBlank(searchModel.getCordName())
                    && StringUtils.isNotBlank(searchModel.getCreateTime())
                    && StringUtils.isNotBlank(searchModel.getEndTime())
                    && StringUtils.isNotBlank(searchModel.getOperateType())
                    && StringUtils.isNotBlank(searchModel.getProvinceCode())
                    && StringUtils.isNotBlank(searchModel.getUsername())
                    && searchModel.getStatus() == null
                    && searchModel.getUserId() == null
            ) {
                List<TaskVo> taskVos = new ArrayList<>();
                TaskCord lastTaskCord = taskCordService.getLastestTaskCordByUserId(user.getId());
                final TaskVo[] taskVo = new TaskVo[1];
                if (lastTaskCord != null) {
                    LinkedList<Task> taskListOfInCordByCordId = taskService.getTaskListOfInCordByCordId(lastTaskCord.getId());
                    ArrayList<Integer> ids = new ArrayList<>();
                    LinkedList<Task> taskListInCord;
                    if (taskListOfInCordByCordId.size() > 0) {
                        taskListOfInCordByCordId.stream().forEach(task -> {
                                    ids.add(task.getId());
                                    taskVo[0] = new TaskVo();
                                    BeanUtils.copyProperties(task, taskVo[0]);
                                    taskVo[0].setCordName(lastTaskCord.getName());
                                    taskVo[0].setCordRemark(lastTaskCord.getRemark());
                                    taskVos.add(taskVo[0]);
                                }
                        );
                        taskListInCord = taskService.getTaskListOfInCordByUserId(user.getId(), ids);
                    } else {
                        taskListInCord = taskService.getTaskListOfInCordByUserId(user.getId(), null);
                    }
                    if (taskListInCord != null) {
                        ConcurrentHashMap<String, TaskCord> map = new ConcurrentHashMap<>();
                        for (Task task : taskListInCord) {
                            taskVo[0] = new TaskVo();
                            BeanUtils.copyProperties(task, taskVo[0]);
                            if (task.getCordId() != null) {
                                if (!map.containsKey(task.getCordId())) {
                                    TaskCord cord = taskCordService.getTaskCordById(task.getCordId());
                                    if (cord != null) {
                                        map.put(cord.getId() + "", cord);
                                        taskVo[0].setCordName(cord.getName());
                                        taskVo[0].setCordRemark(cord.getRemark());
                                    }
                                } else {
                                    TaskCord cord = map.getOrDefault(task.getCordId(), taskCordService.getTaskCordById(task.getCordId()));
                                    taskVo[0].setCordName(cord.getName());
                                    taskVo[0].setCordRemark(cord.getRemark());
                                }
                            }
                            taskVos.add(taskVo[0]);
                        }
//                        taskListOfInCordByCordId.addAll(taskListInCord);
                    }
                    return JsonResult.getInstance().ok(taskVos);
                } else {
                    List<Task> taskListOfNotInCordByUserId = taskService.getTaskListOfNotInCordByUserId(user.getId());
                    for (Task task : taskListOfNotInCordByUserId) {
                        taskVo[0] = new TaskVo();
                        BeanUtils.copyProperties(task, taskVo[0]);
                        taskVos.add(taskVo[0]);
                    }
                    return JsonResult.getInstance().ok(taskVos);
                }
            }
            ArrayList<TaskVo> taskVos = new ArrayList<>();
            List<Task> tasks = taskService.getSearchResult(searchModel);
            TaskVo taskVo;
            ConcurrentHashMap<String, TaskCord> map = new ConcurrentHashMap<>();
            for (Task task : tasks) {
                taskVo = new TaskVo();
                BeanUtils.copyProperties(task, taskVo);
                if (task.getCordId() != null) {
                    if (!map.containsKey(task.getCordId())) {
                        TaskCord cord = taskCordService.getTaskCordById(task.getCordId());
                        if (cord != null) {
                            map.put(cord.getId() + "", cord);
                            taskVo.setCordName(cord.getName());
                            taskVo.setCordRemark(cord.getRemark());
                        }
                    } else {
                        TaskCord cord = map.getOrDefault(task.getCordId(), taskCordService.getTaskCordById(task.getCordId()));
                        taskVo.setCordName(cord.getName());
                        taskVo.setCordRemark(cord.getRemark());
                    }
                }
                taskVos.add(taskVo);
            }

            return JsonResult.getInstance().ok(taskVos);
        }
        return JsonResult.success();
    }

    /**
     * 获取操作日志
     *
     * @param searchModel
     * @param request
     * @return
     */
    @GetMapping("/operate/log")
    public JsonResult<List<OperateLogVo>> getOperateLog(SearchModel searchModel, HttpServletRequest request,
                                                        @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        Object u = request.getSession().getAttribute(TdtStaticConst.USER_SESSION_KEY);
        if (u == null) {
            throw new CommonException(ResultCodeEnum.NO_USER_INFO);
        }
        User user = (User) u;
        searchModel.setUserId(user.getId());
        logger.debug("获取操作日志，搜索条件\t{}", searchModel);
        List<Task> tasks = taskService.getSearchResult(searchModel);
        LinkedList<OperateLogVo> operateLogVos = new LinkedList<>();
        HashSet<Integer> cordIdSet = new HashSet<>();
        TaskCord taskCord;
        OperateLogVo logVo;
        for (Task task : tasks) {
            if (task.getCordId() == null) {
                //单个任务
                logVo = new OperateLogVo();
                logVo.setId(task.getId());
                logVo.setOperateType(task.getOperateType());
                logVo.setCreateTime(task.getCreateTime());
                logVo.setStartTime(task.getStartTime());
                logVo.setEndTime(task.getEndTime());
                logVo.setOperatorName(task.getOperatorName());
                logVo.setStatus(task.getStatus());
                operateLogVos.add(logVo);
            } else {
                //任务组内任务
                if (cordIdSet.contains(task.getCordId())) {
                    continue;
                }
                cordIdSet.add(task.getCordId());
                taskCord = taskCordService.getTaskCordById(task.getCordId());
                logVo = new OperateLogVo();
                logVo.setId(taskCord.getId());
                logVo.setCordName(taskCord.getName());
                logVo.setCreateTime(taskCord.getCreateTime());
                logVo.setStartTime(taskCord.getStartTime());
                logVo.setEndTime(taskCord.getEndTime());
                logVo.setOperatorName(taskCord.getOperatorName());
                logVo.setStatus(taskCord.getStatus());
                operateLogVos.add(logVo);
            }
            if (operateLogVos.size() >= ((pageNo < 1 ? 1 : pageNo) * pageSize)) {
                break;
            }
        }
        List<OperateLogVo> logVoList = operateLogVos.stream().skip((pageNo - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        return JsonResult.getInstance().ok(logVoList);
    }

    /**
     * 获取操作日志
     *
     * @param searchModel
     * @param request
     * @return
     */
    @GetMapping("/operate/log/count")
    public JsonResult<Integer> getOperateLogCount(SearchModel searchModel, HttpServletRequest request) {
        Object u = request.getSession().getAttribute(TdtStaticConst.USER_SESSION_KEY);
        if (u == null) {
            throw new CommonException(ResultCodeEnum.NO_USER_INFO);
        }
        User user = (User) u;
        searchModel.setUserId(user.getId());
        List<Task> tasks = taskService.getSearchResult(searchModel);
        HashSet<Integer> cordIdSet = new HashSet<>();
        int count = 0;
        for (Task task : tasks) {
            if (task.getCordId() == null) {
                //单个任务
                count++;
            } else {
                //任务组内任务
                if (cordIdSet.contains(task.getCordId())) {
                    continue;
                }
                cordIdSet.add(task.getCordId());
                count++;
            }
        }
        logger.debug("根据查询条件获取操作日志数量\t[ {} ]", count);
//        int notInCordCount = taskService.getTaskOfNotInCordCountByCondition(searchModel);
//        List<TaskCord> cords = taskCordService.getSearchResult(searchModel);
        return JsonResult.getInstance().ok(count);
    }

    @PostMapping("/task/edit")
    public JsonResult<Task> updateTaskById(HttpServletRequest request, Task task) {
        Object u = request.getSession().getAttribute(TdtStaticConst.USER_SESSION_KEY);
        if (u == null) {
            throw new CommonException(ResultCodeEnum.NO_USER_INFO.getCode(), ResultCodeEnum.NO_USER_INFO.getMsg());
        }
        if (task.getId() == null) {
            throw new CommonException(500, "缺少主键重要参数");
        }
        Task taskById = taskService.getTaskById(task.getId());
        BeanUtils.copyProperties(task, taskById, getNullPropertyNames(task));
        task = checkTask(taskById, request);

        boolean result = taskService.updateTask(task);
        if (result) {
            return JsonResult.getInstance().ok(task);
        }
        return JsonResult.fail();
    }

    /**
     * 判断用户是不是超级管理员
     *
     * @param user
     * @return true：是 false：不是
     */
    private boolean checkUserSuperAdminAuth(User user) {
        return StringUtils.equals(String.valueOf(user.getRole()), TdtStaticConst.ADMIN_ROLE) && StringUtils.equals(user.getProvinceCode(), TdtStaticConst.TDT_PROVINCE_CODE);
    }

    /**
     * 获取空属性
     *
     * @param source 源
     * @return 数据集
     */
    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
