package com.zzh.contest.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user_group")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGroup {
    @TableId
    private Integer id;
    private Integer userId;
    private Integer groupId;
}
