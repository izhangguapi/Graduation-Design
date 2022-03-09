package pers.zzh.competition.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author 张恣豪
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    /**
     * 响应码，200是正常
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应数据
     */
    private Object data;

//    响应时间
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date time = new Date();

    //    public Result(Integer code, String msg) {
//        this.code = code;
//        this.msg = msg;
//    }

    public Result() {
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Result(Object data) {
        this.data = data;
    }
    public Result(String msg) {
        this.msg = msg;
    }


}
