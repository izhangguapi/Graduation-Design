package com.zzh.contest.utils.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 统一返回类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    // 响应类型
    private Boolean status;
    // 响应码，200是正常
    private Integer code;
    // 响应消息
    private String msg;
    // 响应数据
    private Object data;
    // 响应时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date time;


    public Result(boolean status, int code, String msg, Date time) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.time = time;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, Date time) {
        this.code = code;
        this.msg = msg;
        this.time = time;
    }

    // 响应成功返回数据
    public static Result success(ResultCode resultCode, Object data) {
        return new Result(true, resultCode.getCode(), resultCode.getMsg(), data, new Date());
    }

    public static Result success(ResultCode resultCode) {
        return new Result(true, resultCode.getCode(), resultCode.getMsg(), new Date());
    }

    public static Result failure(ResultCode resultCode) {
        return new Result(resultCode.getCode(), resultCode.getMsg(), new Date());
    }
}