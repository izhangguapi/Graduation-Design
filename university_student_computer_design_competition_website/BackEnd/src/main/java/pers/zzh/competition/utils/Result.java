package pers.zzh.competition.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Result {
    // 逻辑判断
    private Boolean flag;
    // 响应码，200是正常，非200表示异常
    private int code;
    // 响应消息
    private String msg;
    // 响应数据
    private Object data;
/*
    // 响应时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time = new Date();
*/
    public Result(Integer code, String msg,Boolean flag) {
        this.code = code;
        this.msg = msg;
        this.flag = flag;
    }
    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Result(Integer code, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
