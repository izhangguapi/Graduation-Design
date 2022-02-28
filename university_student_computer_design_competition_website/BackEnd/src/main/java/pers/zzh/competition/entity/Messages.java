package pers.zzh.competition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author 张恣豪
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Messages {
    @TableId
    private Integer messageId;
    private Integer recipient;
    private String title;
    private String text;
    private Integer sender;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
    private Boolean state;
    /**
     * 链表查询-用户表字段：姓名
     */
    @TableField(exist = false)
    private String name;
}
