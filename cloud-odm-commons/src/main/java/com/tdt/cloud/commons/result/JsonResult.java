package com.tdt.cloud.commons.result;

import lombok.Data;

/**
 * @author Mr.superbeyone
 * @project cloud-odm
 * @className JsonResult
 * @description
 * @date 2020-12-10 16:29
 **/

@Data
public class JsonResult<T> {

    private Integer code;

    private String msg;

    private T data;


    public JsonResult() {
    }

    public static JsonResult getInstance() {
        return new JsonResult<>();
    }

    public JsonResult(int code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public JsonResult ok(T data) {
        return new JsonResult(200, "操作成功", data);
    }

    public JsonResult success(ResultCodeEnum resultCodeEnum, T data) {
        return new JsonResult(resultCodeEnum.getCode(), resultCodeEnum.getMsg(), data);
    }

    public static JsonResult success() {
        return new JsonResult(200, "操作成功", null);
    }

    public static JsonResult success(ResultCodeEnum resultCodeEnum) {
        return new JsonResult(resultCodeEnum.getCode(), resultCodeEnum.getMsg(), null);
    }

    public static JsonResult fail(int status, String message) {
        return new JsonResult(status, message, null);
    }

    public static JsonResult fail(ResultCodeEnum resultCodeEnum) {
        return new JsonResult(resultCodeEnum.getCode(), resultCodeEnum.getMsg(), null);
    }

    public static JsonResult fail() {
        return new JsonResult(ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getMsg(), null);
    }

  
}

