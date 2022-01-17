package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import pers.zzh.competition.mapper.UsersMapper;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.service.UsersService;

import java.util.List;
import java.util.Map;

/**
 * 业务逻辑实现类
 * @author 张恣豪
 * @since 2022-1-11
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    // 页链表查询
    @Override
    public Page<Map<String, Object>> selectListPage(int currentPage, int pageSize) {
        //新建分页
        Page<Map<String, Object>> page = new Page<>(currentPage, pageSize);
        //返回结果
        return page.setRecords(baseMapper.selectAllUsersAndGroups(page));
    }

    // 登录功能（账号为手机号或邮箱）
    @Override
    public List<Users> login(String phone, String email, String password) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.and(Wrapper -> Wrapper.eq("phone",phone).or().eq("email",email));
        qw.eq("password",password);
        System.out.println("打印phone："+phone);
        System.out.println("打印email："+email);
        System.out.println("打印password："+password);
        return baseMapper.selectList(qw);
    }
}
