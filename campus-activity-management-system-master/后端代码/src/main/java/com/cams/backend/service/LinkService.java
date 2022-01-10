package com.cams.backend.service;

import com.cams.backend.common.lang.Result;
import com.cams.backend.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cams
 * @since 2021-09-08
 */
public interface LinkService extends IService<Link> {

    /**
     * 活动报名
     * 函数声明
     */
    Result signActivity(Link link);

    Result signupActivity(int aid,int uid);

    /**
     * 退出活动
     * 函数声明
     */
    Result quitActivity(int aid,int uid);

    /**
     * 查看活动成员名单
     * 函数声明
     */
    Result joinerList(int aid);

    /**
     * 查看我的加入
     * 函数声明
     */
    Result myJoinList(int uid);


}
