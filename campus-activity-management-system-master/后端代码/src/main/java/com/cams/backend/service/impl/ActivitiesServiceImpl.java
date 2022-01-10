package com.cams.backend.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cams.backend.common.lang.Result;
import com.cams.backend.entity.Activities;
import com.cams.backend.entity.Link;
import com.cams.backend.entity.Users;
import com.cams.backend.mapper.ActivitiesMapper;
import com.cams.backend.mapper.LinkMapper;
import com.cams.backend.service.ActivitiesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cams.backend.service.UsersService;
import com.cams.backend.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cams
 * @since 2021-09-06
 */
@Service
public class ActivitiesServiceImpl extends ServiceImpl<ActivitiesMapper, Activities> implements ActivitiesService {


    @Autowired
    JwtUtils jwtUtils;


    @Resource
    private ActivitiesMapper activityMapper;

    @Resource
    private LinkMapper linkMapper;


    /**
     * 发布新活动
     * 方法实现
     *
     * @author cams
     */
    @Override
    public Result newActivity(Activities activity) {
        if(activity.getAName()==""){
            return Result.fail("活动名称不能为空！");
        }else{
            activityMapper.insert(activity);
            Link link=new Link();
            link.setAid(activity.getAId());
            link.setAname(activity.getAName());
            link.setUid(activity.getUId());
            link.setUname(activity.getUUsername());
            linkMapper.insert(link);
            return Result.succeed(MapUtil.builder()
                    .put("aid", activity.getAId())
                    .put("aname", activity.getAName())
                    .put("uid", activity.getUId())
                    .map());
        }

    }



    /**
     * 查看活动列表
     * 方法实现
     * @author cams
     */
    @Override
    public Result allActivities() {
        List<Activities> activitiesList = activityMapper.selectList(null);
        return Result.succeed(activitiesList);
    }

    /**
     * 查看未参加活动
     * 方法实现
     * @author cams
     */
    @Override
    public Result unjoinActivities(int uid) {
        List<Link> linkList = linkMapper.selectList(new QueryWrapper<Link>().eq("uid", uid));
        if(linkList.isEmpty()){
            List<Activities> activitiesList = activityMapper.selectList(null);
            return Result.succeed(activitiesList);
        }else{
            List collect = linkList.stream().map(Link::getAid).collect(Collectors.toList());
            QueryWrapper<Activities> wapper=new QueryWrapper<>();
            wapper.notIn("a_id", collect);
            List<Activities> activitiesList = activityMapper.selectList(wapper);
            return Result.succeed(activitiesList);
        }

    }


    /**
     * 根据类别查看活动
     * 方法实现
     * @author cams
     */
    @Override
    public Result sortActivities(String asort) {
        List<Activities> activitiesList = activityMapper.selectList(new QueryWrapper<Activities>().eq("a_sort", asort));
        return Result.succeed(activitiesList);
    }

    /**
     * 查看活动详情
     * 函数声明
     * @author cams
     */
    @Override
    public Result findByAid(int aid,int uid) {
        Activities activity =  activityMapper.selectOne(new QueryWrapper<Activities>().eq("a_id", aid));
        if(activity==null){
            return Result.fail("该活动不存在");
        }else{
            Link link=linkMapper.selectOne(new QueryWrapper<Link>().eq("aid", aid).eq("uid",uid));
            if(link==null){
                return Result.succeed(MapUtil.builder()
                        .put("aid",activity.getAId())
                        .put("aname", activity.getAName())
                        .put("atime",activity.getATime())
                        .put("uusername",activity.getUUsername())
                        .put("aadd",activity.getAAdd())
                        .put("acontent",activity.getAContent())
                        .put("asort",activity.getASort())
                        .put("uid", activity.getUId())
                        .put("astate",false)
                        .map());
            }else{
                return Result.succeed(MapUtil.builder()
                        .put("aid",activity.getAId())
                        .put("aname", activity.getAName())
                        .put("atime",activity.getATime())
                        .put("uusername",activity.getUUsername())
                        .put("aadd",activity.getAAdd())
                        .put("acontent",activity.getAContent())
                        .put("asort",activity.getASort())
                        .put("uid", activity.getUId())
                        .put("astate",true)
                        .map());
            }

        }
    }

    /**
     * 删除活动
     * 函数声明
     * @author cams
     */
    @Override
    public Result deleteActivity(int aid,int uid) {
        Activities activity =  activityMapper.selectOne(new QueryWrapper<Activities>().eq("a_id", aid));
        if(activity==null){
            return Result.fail("该活动不存在");
        }else{
                linkMapper.delete(new QueryWrapper<Link>().eq("aid", aid));
                activityMapper.deleteById(aid);
                return Result.succeed(null);

        }
    }


    /**
     * 查看我的发起
     * 函数声明
     */
    @Override
    public Result myHostList(int uid) {
        List<Activities> hostList = activityMapper.selectList(new QueryWrapper<Activities>().eq("u_id", uid));
        return Result.succeed(hostList);
    }

    /**
     * 编辑活动
     * 方法实现
     *
     * @author cams
     */
    @Override
    public Result updateActivity(Activities activity) {
        activityMapper.updateById(activity);
        return Result.succeed(MapUtil.builder()
                .put("aid", activity.getAId())
                .put("aname", activity.getAName())
                .put("uid", activity.getUId())
                .map());
    }

}
