package com.cams.backend.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cams.backend.common.dto.LoginDto;
import com.cams.backend.common.lang.Result;
import com.cams.backend.entity.Activities;
import com.cams.backend.entity.Link;
import com.cams.backend.entity.Users;
import com.cams.backend.mapper.ActivitiesMapper;
import com.cams.backend.mapper.LinkMapper;
import com.cams.backend.mapper.UsersMapper;
import com.cams.backend.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cams.backend.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  业务逻辑实现类
 * </p>
 *
 * @author cams
 * @since 2021-09-06
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    JwtUtils jwtUtils;

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private LinkMapper linkMapper;

    @Resource
    private ActivitiesMapper activityMapper;

    /**
     * 用户注册
     * 方法实现
     * @author cams
     */
    @Override
    public Result register(Users user) {
        QueryWrapper<Users> userQueryWrapper = Wrappers.query();
        userQueryWrapper.like("username", user.getUsername());
        Users temp = usersMapper.selectOne(userQueryWrapper);
        if (temp != null) {
            return Result.fail("该用户名已被注册！");

        } else {
            usersMapper.insert(user);
            return Result.succeed(MapUtil.builder()
                    .put("id", user.getId())
                    .put("username", user.getUsername())
                    .put("status",user.getStatus())
                    .map());
        }

    }

    /**
     * 用户登录
     * 方法实现
     * @author cams
     */
    @Override
    public Result login(LoginDto loginDto, HttpServletResponse response) {
        Users user =usersMapper.selectOne(new QueryWrapper<Users>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");

        if(!user.getPassword().equals(loginDto.getPassword())){
            return Result.fail("密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.succeed(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("status",user.getStatus())
                .map()
        );
    }

    /**
     * 用户登出
     * 函数声明
     */

    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.succeed(null);
    }


    /**
     * 查看个人信息
     * 方法实现
     */
    @Override
    public Result findById(int id) {
        Users user = usersMapper.selectOne(new QueryWrapper<Users>().eq("id", id));
        if(user==null){
            return Result.fail("用户不存在");
        }else{
            return Result.succeed(MapUtil.builder()
                    .put("id", user.getId())
                    .put("username", user.getUsername())
                    .put("status",user.getStatus())
                    .map()
            );
        }
    }

    /**
     * 用户删除
     * @param uid
     * @return
     */

    @Override
    public Result deleteUserById(int uid) {
        List<Link> linkList = linkMapper.selectList(new QueryWrapper<Link>().eq("uid", uid));
        if(!linkList.isEmpty()){
            List linkCollect = linkList.stream().map(Link::getId).collect(Collectors.toList());
            linkMapper.deleteBatchIds(linkCollect);
        }


        List<Activities> activitiesList = activityMapper.selectList(new QueryWrapper<Activities>().eq("u_id", uid));
        if(!activitiesList.isEmpty()){
            List activitiesCollect = activitiesList.stream().map(Activities::getAId).collect(Collectors.toList());

            QueryWrapper<Link> wapper=new QueryWrapper<>();
            wapper.in("aid", activitiesCollect);
            List<Link> tempList = linkMapper.selectList(wapper);
            if(!tempList.isEmpty()){
                List collect = tempList.stream().map(Link::getId).collect(Collectors.toList());
                linkMapper.deleteBatchIds(collect);
            }

            activityMapper.deleteBatchIds(activitiesCollect);
        }

        usersMapper.deleteById(uid);
        return Result.succeed(null);
    }



    /**
     * 修改密码
     * 方法实现
     */
    @Override
    public Result updateUserInfo(Users user) {
            usersMapper.updateById(user);
            return Result.succeed(MapUtil.builder()
                    .put("id", user.getId())
                    .put("username", user.getUsername())
                    .put("status",user.getStatus())
                    .map());

    }

    /**
     * 查看用户列表
     * 方法实现
     * @author cams
     */
    @Override
    public Result allUsers() {
        List<Users> usersList = usersMapper.selectList(null);
        return Result.succeed(usersList);
    }
}
