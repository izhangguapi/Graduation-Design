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
@TableName(value = "groups")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Groups {
    @TableId
    private Integer groupId;
    private String groupName;
    private String encoding;
    // 链表查询-自定义字段：数量
    @TableField(exist = false)
    private Integer number;
}
