package com.zzh.contest.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "messages")
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
    private Boolean status;
    // 链表查询-用户表字段：姓名
    @TableField(exist = false)
    private String name;
}