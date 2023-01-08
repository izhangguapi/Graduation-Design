package com.zzh.contest.utils.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 统一返回类
 */
@Data
@AllArgsConstructor
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

    // 响应成功返回数据
    public static Result success(ResultCode resultCode, Object data) {
        return new Result(true, resultCode.getCode(), resultCode.getMsg(), data, new Date());
    }

    public static Result success(ResultCode resultCode) {
        return new Result(true, resultCode.getCode(), resultCode.getMsg(), null, new Date());
    }

    public static Result failure(ResultCode resultCode) {
        return new Result(false, resultCode.getCode(), resultCode.getMsg(), null, new Date());
    }
}
