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
@TableName(value = "school")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class School {
    @TableId
    private Integer id;
    private String schoolId;
    private String schoolName;
    private String provinceId;
    private String provinceName;
    private String cityId;
    private String cityName;
    private String level;
    private String department;
    private String other;
}
