package com.cams.backend.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cams.backend.common.lang.Result;
import com.cams.backend.entity.Activities;
import com.cams.backend.entity.Link;
import com.cams.backend.entity.Users;
import com.cams.backend.mapper.ActivitiesMapper;
import com.cams.backend.mapper.LinkMapper;
import com.cams.backend.mapper.UsersMapper;
import com.cams.backend.service.LinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2021-09-08
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Resource
    private LinkMapper linkMapper;

    @Resource
    private ActivitiesMapper activityMapper;

    @Resource
    private UsersMapper userMapper;

    /**
     * 活动报名(传body)
     * 方法实现
     */
    @Override
    public Result signActivity(Link link) {

        Link oldLink = linkMapper.selectOne(new QueryWrapper<Link>()
                .eq("aid", link.getAid()).eq("uid",link.getUid()));

        if(oldLink==null){
            linkMapper.insert(link);
            return Result.succeed(MapUtil.builder()
                    .put("aid", link.getAid())
                    .put("aname", link.getAname())
                    .put("uid", link.getUid())
                    .put("uname",link.getUname())
                    .map());
        } else{
            return Result.succeed(200,"你已报名该活动",null);
        }

    }

    /**
     * 活动报名(传参数版)
     * 方法实现
     */
    @Override
    public Result signupActivity(int aid,int uid) {

        List<Link> linkList = linkMapper.selectList(new QueryWrapper<Link>().eq("aid", aid).eq("uid",uid));

        if(linkList.isEmpty()){
            Link link=new Link();
            Activities activity =  activityMapper.selectOne(new QueryWrapper<Activities>().eq("a_id", aid));
            Users user=userMapper.selectOne(new QueryWrapper<Users>().eq("id", uid));
            link.setAid(aid);
            link.setAname(activity.getAName());
            link.setUid(uid);
            link.setUname(user.getUsername());
            linkMapper.insert(link);
            return Result.succeed(MapUtil.builder()
                    .put("aid", link.getAid())
                    .put("aname", link.getAname())
                    .put("uid", link.getUid())
                    .put("uname",link.getUname())
                    .map());
        }else{
            return Result.succeed(200,"你已报名该活动",null);
        }


    }

    /**
     * 退出活动
     * 方法实现
     */
    @Override
    public Result quitActivity(int aid,int uid) {
        Link link = linkMapper.selectOne(new QueryWrapper<Link>().eq("aid", aid).eq("uid",uid));

        if(link==null){
            return Result.succeed(200,"你未报名该活动",null);
        }else{
            linkMapper.deleteById(link.getId());
            return Result.succeed(null);
        }
    }


    /**
     * 查看活动成员名单
     * 函数声明
     */
    @Override
    public Result joinerList(int aid) {
        List<Link> linkList = linkMapper.selectList(new QueryWrapper<Link>().eq("aid", aid));
        return Result.succeed(linkList);
    }

    /**
     * 查看我的加入
     * 函数声明
     */
    @Override
    public Result myJoinList(int uid) {
        List<Link> linkList = linkMapper.selectList(new QueryWrapper<Link>().eq("uid", uid));
        if(linkList.isEmpty()){
            return Result.succeed(200,"未参与任何活动",null);
        }else{
            List collect = linkList.stream().map(Link::getAid).collect(Collectors.toList());
            QueryWrapper<Activities> wapper=new QueryWrapper<>();
            wapper.in("a_id", collect);
            List<Activities> activitiesList = activityMapper.selectList(wapper);
            return Result.succeed(activitiesList);
        }

    }
}
