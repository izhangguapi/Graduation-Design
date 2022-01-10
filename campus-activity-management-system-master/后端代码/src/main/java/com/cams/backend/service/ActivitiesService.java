package com.cams.backend.service;

import com.cams.backend.common.lang.Result;
import com.cams.backend.entity.Activities;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cams.backend.entity.Users;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cams
 * @since 2021-09-06
 */
public interface ActivitiesService extends IService<Activities> {

    /**
     * 发布新活动
     * 函数声明
     * @author cams
     */
    Result newActivity(Activities activity);

    /**
     * 查看活动列表
     * 函数声明
     * @author cams
     */
    Result allActivities();

    /**
     * 查看未参加活动
     * 方法实现
     * @author cams
     */
    public Result unjoinActivities(int uid);

    /**
     * 查看活动详情
     * 函数声明
     * @author cams
     */
    Result findByAid(int aid,int uid);

    /**
     * 根据活动编号删除活动
     * 函数声明
     * @author cams
     */
    Result deleteActivity(int aid,int uid);

    /**
     * 根据活动类别查看活动
     * 函数声明
     * @author cams
     */
    Result sortActivities(String asort);

    /**
     * 查看我的发起
     * 函数声明
     */
    Result myHostList(int uid);

    /**
     * 编辑活动
     * 函数声明
     */
    Result updateActivity(Activities activity);

}
