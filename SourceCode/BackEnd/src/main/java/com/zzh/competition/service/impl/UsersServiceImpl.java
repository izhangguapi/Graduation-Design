package com.zzh.competition.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.competition.entity.Users;
import com.zzh.competition.mapper.UsersMapper;
import com.zzh.competition.service.UsersService;
import com.zzh.competition.utils.JwtUtils;
import com.zzh.competition.vo.Result;
import com.zzh.competition.vo.ResultCode;
import com.zzh.competition.vo.params.LoginParam;
import com.zzh.competition.vo.params.PageQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    /**
     * 分页链表查询
     *
     * @param pageQuery 分页查询对象
     * @return 结果对象
     */
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

    /**
     * 登录功能（账号为手机号或邮箱）
     *
     * @param phone    手机号
     * @param email    邮箱
     * @param password 密码
     * @return 登录结果
     */
    @Override
    public Result selectPhoneEmailPassword(String phone, String email, String password) {
        LambdaQueryWrapper<Users> qw = new LambdaQueryWrapper<>();
        qw.eq(StrUtil.isNotBlank(phone), Users::getPhone, phone)
                .eq(StrUtil.isNotBlank(email), Users::getEmail, email)
                // 增加查询效率
                .last("LIMIT 1");
        Users user = baseMapper.selectOne(qw);

        try {
            if (user.getPassword().equals(password)) {
                return Result.success(ResultCode.LOGIN_SUCCESS, JwtUtils.createToken(user.getName(), user.getUserId(), user.getGroupId(), user.getGroupId().equals(1)));
            } else {
                return Result.failure(ResultCode.LOGIN_FAIL);
            }
        } catch (Exception e) {
            return Result.failure(ResultCode.LOGIN_FAIL);
        }
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
     * @param phoneAndEmail 登录对象
     * @return true或false
     */
    @Override
    public Result selectPhoneEmail(LoginParam phoneAndEmail) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.eq("phone", phoneAndEmail.getPhone())
                .or().eq("email", phoneAndEmail.getEmail());
        return baseMapper.selectList(qw).isEmpty()
                ? Result.success(ResultCode.SELECT_SUCCESS)
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
