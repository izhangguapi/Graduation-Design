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
 * @author 张恣豪
 * @since 2022-1-11
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    // 分页链表查询
    @Override
    public Page<Users> selectListPage(int currentPage, int pageSize) {
        Page<Users> page = new Page<>(currentPage,pageSize); // 创建分页对象
        QueryWrapper<Users> qw = new QueryWrapper<>(); // 创建条件构造器
        qw.select("*"); //查询全部
        qw.last("inner join groups g ON users.group_id = g.group_id order by user_id"); //尾部添加链表条件
        //返回结果
        return baseMapper.selectPage(page,qw);
    }

    // 登录功能（账号为手机号或邮箱）
    @Override
    public List<Users> selectPhoneEmailPassword(String phone, String email, String password) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.and(Wrapper -> Wrapper.eq("phone",phone).or().eq("email",email));
        qw.eq("password",password);
        System.out.println("打印phone："+phone);
        System.out.println("打印email："+email);
        System.out.println("打印password："+password);
        return baseMapper.selectList(qw);
    }

    // 注册功能
    @Override
    @Options(useGeneratedKeys = true,keyProperty = "groupId",keyColumn = "groupId")
    public int insertUsers(Users users) {
        try {
            baseMapper.insert(users);
        }catch (Exception e){
            return 0;
        }
        return users.getUserId();
    }

    // 查询手机号和邮箱是否存在
    @Override
    public Boolean selectPhoneEmail(Login phoneAndEmail) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.select("*").eq("phone",phoneAndEmail.getPhone())
                .or().eq("email",phoneAndEmail.getEmail());
        return baseMapper.selectList(qw).isEmpty();
    }
}
