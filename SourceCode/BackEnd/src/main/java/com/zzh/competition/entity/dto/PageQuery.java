package com.zzh.competition.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于用户表分页查询,用户表实体类继承于此类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageQuery {
    @TableField(exist = false)
    private String query;
    @TableField(exist = false)
    private Integer currentPage;
    @TableField(exist = false)
    private Integer pageSize;
}
