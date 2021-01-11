package com.tdt.cloud.commons.result;

/**
 * @author superbeyone
 */

public enum ResultCodeEnum {

    /**
     * 统一状态码返回结果封装
     */
    SUCCESS(200, "操作成功"),

    FAIL(1000, "操作失败"),

    DATA_DIVISION_IS_NULL(1001, "行政区划列表数据为空"),

    IP_IS_NOT_RIGHT(1002, "IP不合法"),

    MONGO_CONN_IS_ERROR(1003, "Mongo连接信息错误"),

    NO_USER_INFO(2001, "无法获取用户信息"),

    PARAM_CAN_NOT_BE_NULL(2002, "重要参数不能为空"),

    USERNAME_OR_PASSWORD_ERROR(2003, "用户名或密码输入错误"),

    MUST_ACTIVATE_MONGO_CONN_FIRST(2004, "请先选择需要激活的Mongo连接"),

    USER_HAD_EXISTED(2005, "用户名已存在"),

    THE_MONGO_DB_NAME_HAD_EXISTED(2006, "该数据库名已存在"),

    NO_TASK_GROUP_NAME(3001, "任务组名称不能为空"),

    EXIST_READY_TASK_CORD(3002, "已存在准备中的任务组，不可再新建"),

    TASK_CORD_STATUS_NOT_READY(3003, "任务组状态为非就绪状态"),

    TASK_CORD_NOT_EXIST(3004, "任务组不存在"),

    NO_CAN_RUN_TASK_CORD(3005, "暂无可执行的任务组"),

    NO_OTHER_RUNNING_TASK_CORD(3006, "没有正在运行中的任务组"),

    NO_WAITING_TASK_CORD(3007, "没有就绪状态下的任务组"),

    CAN_NOT_STOP_TASK(3008, "没有可终止的任务"),

    EXIST_OTHER_RUNNING_TASK_ON_SAME_DATABASE(3009, "已存在对当前库操作的其它运行中的任务"),

    EXIST_OTHER_RUNNING_TASK_OF_SAME_USER(3010, "存在正在运行中的任务组，<br/>您可选择排队等待操作"),

    NO_TASK(3011, "没有任务信息"),

    NO_OPERATOR_AUTHORITY(4003, "没有操作权限"),

    TEST_CONN_SUCCESS(200, "连接成功"),

    TEST_CONN_FAIL(5000, "连接失败"),

    NO_DATA(5001, "查无此数据"),

    REMOTE_CONN_ADDR_NOT_EXIST(5002, "远程连接地址无效");


    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }


}
