package com.cams.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cams
 * @since 2021-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Activities implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动编号
     */
    @TableId(value = "a_id", type = IdType.AUTO)
    private Integer aId;

    /**
     * 活动名称
     */
    private String aName;

    /**
     * 发起时间
     */
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private String aTime;

    /**
     * 发起人用户名
     */
    private String uUsername;

    /**
     * 活动地点
     */
    private String aAdd;

    /**
     * 活动内容
     */
    private String aContent;

    /**
     * 活动类别
     */
    private String aSort;


    /**
     * 发起人账号
     */
    private Integer uId;


}
