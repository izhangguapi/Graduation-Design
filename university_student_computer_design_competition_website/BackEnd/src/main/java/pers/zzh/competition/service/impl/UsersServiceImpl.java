package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Service;
import pers.zzh.competition.common.entity.Login;
import pers.zzh.competition.mapper.UsersMapper;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.service.UsersService;

import java.util.List;

/**
 * 业务逻辑实现类
 *
 * @author 张恣豪
 * @since 2022-1-11
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    // 分页链表查询
    @Override
    public Page<Users> selectListPage(int currentPage, int pageSize) {
        Page<Users> page = new Page<>(currentPage, pageSize); // 创建分页对象
        QueryWrapper<Users> qw = new QueryWrapper<>(); // 创建条件构造器
        qw.select("*"); //查询全部
        qw.last("inner join groups g ON users.group_id = g.group_id order by user_id"); //尾部添加链表条件
        //返回结果
        return baseMapper.selectPage(page, qw);
    }

    // 登录功能（账号为手机号或邮箱）
    @Override
    public Users selectPhoneEmailPassword(String phone, String email, String password) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.select("user_id,name,group_id");
        qw.and(Wrapper -> Wrapper.eq("phone", phone).or().eq("email", email));
        qw.eq("password", password);
        return baseMapper.selectOne(qw);
    }

    // 注册功能
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "groupId", keyColumn = "groupId")
    public int insertUsers(Users users) {
        try {
            baseMapper.insert(users);
        } catch (Exception e) {
            return 0;
        }
        return users.getUserId();
    }

    // 修改个人资料
    @Override
    public Boolean updateUser(Users users) {
        try {
            if (baseMapper.updateById(users) != 0) return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    // 查询手机号和邮箱是否存在
    @Override
    public Boolean selectPhoneEmail(Login phoneAndEmail) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.select("name,sex,birthday,school,address,phone,phone,groupName").eq("phone", phoneAndEmail.getPhone())
                .or().eq("email", phoneAndEmail.getEmail());
        return baseMapper.selectList(qw).isEmpty();
    }

    // 查询单个用户信息
    @Override
    public Users selectById(int uid) {
        QueryWrapper<Users> qw = new QueryWrapper<>(); // 创建条件构造器
        qw.select("address,birthday,email,group_name,name,phone,school,sex"); //查询全部
        qw.last("inner join groups g ON users.group_id = g.group_id where user_id = " + uid); //尾部添加链表条件
        return baseMapper.selectOne(qw);
    }
}
