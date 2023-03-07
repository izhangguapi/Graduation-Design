package com.zzh.contest.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.contest.entity.Role;
import com.zzh.contest.entity.Users;
import com.zzh.contest.entity.dto.Login;
import com.zzh.contest.entity.dto.PageQuery;
import com.zzh.contest.mapper.UsersMapper;
import com.zzh.contest.service.RoleService;
import com.zzh.contest.service.UsersService;
import com.zzh.contest.utils.JwtUtils;
import com.zzh.contest.utils.RedisCache;
import com.zzh.contest.utils.result.Result;
import com.zzh.contest.utils.result.ResultCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService, UserDetailsService {

    // 分页链表查询
    @Override
    public Page<Users> selectListPage(PageQuery pageQuery) {
        // 创建分页对象
        Page<Users> page = new Page<>(pageQuery.getCurrentPage(), pageQuery.getPageSize());
        // 创建条件构造器
        QueryWrapper<Users> qw = new QueryWrapper<>();
        // 查询全部，尾部添加链表条件
        String sql = "LEFT JOIN groups ON users.group_id = groups.group_id";
        if (pageQuery.getQuery() != null) {
            sql += "WHERE CONCAT(`name`,school,phone) LIKE '%" + pageQuery.getQuery().trim() + "%'";
        }

        qw.select("user_id,`name`,sex,school,birthday,phone,address,email,group_name")
                .last(sql);
        return baseMapper.selectPage(page, qw);
    }

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    RedisCache redisCache;

    // 登录功能（账号为手机号或邮箱）
    @Override
    public Result login(Users users) {
        // 使用ProviderManager auth方法进行验证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(users.getPhone(), users.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        
        // 生成jwt
        Login login = (Login) (authentication.getPrincipal());
        String jwt = JwtUtils.createToken(login.getUsers().getUserId());
        login.setToken(jwt);
        // 封装成前端需要的数据
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        // 將用戶信息放入Redis
        redisCache.setCacheObject("login:" + login.getUsers().getUserId(), login, 7, TimeUnit.DAYS);
        return Result.success(ResultCode.LOGIN_SUCCESS, map);
    }

    /**
     * 退出登录
     */
    @Override
    public Result logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Login login = (Login) authentication.getPrincipal();
        Integer uid = login.getUsers().getUserId();
        if (redisCache.deleteObject("login:" + uid)) {
            return Result.success(ResultCode.LOGOUT_SUCCESS);
        }
        return Result.success(ResultCode.LOGOUT_FAIL);
    }

    @Resource
    RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<Users> qw = new LambdaQueryWrapper<>();
        qw.eq(StrUtil.isNotBlank(username), Users::getPhone, username)
                .or().eq(StrUtil.isNotBlank(username), Users::getEmail, username);
        Users user = baseMapper.selectOne(qw);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }
        // 查询用户
        List<Role> role = roleService.getUserRole(user.getUserId());
        return new Login(user, role);
    }

    /**
     * 注册功能
     *
     * @param users 对象
     * @return 用户id
     */
    @Override
    public Result insertUsers(Users users) {
        users.setPassword(DigestUtils.md5DigestAsHex(users.getPassword().getBytes()));
        try {
            baseMapper.insert(users);
        } catch (Exception e) {
            return Result.failure(ResultCode.REGISTER_FAIL);
        }
        return Result.success(ResultCode.REGISTER_SUCCESS);
    }

    /**
     * 查询手机号和邮箱是否存在
     *
     * @param users 登录对象
     * @return true或false
     */
    @Override
    public Result selectPhoneEmail(Users users) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.eq("phone", users.getPhone())
                .or().eq("email", users.getEmail());
        return baseMapper.selectList(qw).isEmpty()
                ? Result.success(ResultCode.SELECT_SUCCESS, true)
                : Result.failure(ResultCode.ACCOUNT_EXIST);
    }

    /**
     * 查询单个用户信息
     *
     * @param uid 用户id
     * @return 该id的用户信息
     */
    @Override
    public Result selectById(int uid) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.select("address,birthday,email,group_name,name,phone,school,sex")
                .last("inner join groups g ON users.group_id = g.group_id where user_id = " + uid);
        return Result.success(ResultCode.SELECT_SUCCESS, baseMapper.selectOne(qw));
    }

    @Override
    public List<Users> selectByGid(Integer gid) {
        // 根据组id查询该组下有哪用户，生成一个用户id列表
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.select("user_id").eq("group_id", gid);
        return baseMapper.selectList(qw);
    }
}
