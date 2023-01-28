package com.zzh.contest.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "scores")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Scores {
    @TableId
    private Integer scoresId;
    private Integer contestId;
    private String contestant;
    private Boolean status;
    private String judge;
    private String text;
    private Integer result;
    // 链表查询-用户表字段：用户id,姓名，学校，手机号
    @TableField(exist = false)
    private Integer userId;
    @TableField(exist = false)
    private String name;
    @TableField(exist = false)
    private String school;
    @TableField(exist = false)
    private String phone;
    // 链表查询-自定义字段：排名
    @TableField(exist = false)
    private Integer ranking;
    // 链表查询-比赛表字段：比赛标题
    @TableField(exist = false)
    private String contestTitle;
}
