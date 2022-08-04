package pers.zzh.competition.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author 张恣豪
 */
@Data
@AllArgsConstructor
public class Result {

    private boolean status;
    /**
     * 响应码，200是正常
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;
    /**
     * 响应时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date time;

    public static Result success(int code,String msg, Object data) {
        return new Result(true, code, msg, data, new Date());
    }
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
