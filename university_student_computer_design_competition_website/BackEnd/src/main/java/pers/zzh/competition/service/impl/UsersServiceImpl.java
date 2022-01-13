package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import pers.zzh.competition.dao.UsersDao;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.service.UsersService;

import java.util.Map;

/**
 * 业务逻辑实现类
 * @author 张恣豪
 * @since 2022-1-11
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersDao, Users> implements UsersService {

    /**
     * 分页链表查询
     *
     * @param currentPage 第几页
     * @param pageSize    分几页
     * @return 列表
     */
    @Override
    public Page<Map<String, Object>> selectListPage(int currentPage, int pageSize) {
        //新建分页
        Page<Map<String, Object>> page = new Page<>(currentPage, pageSize);
        //返回结果
        return page.setRecords(baseMapper.selectAllUsersAndGroups(page));
    }

    /**
     * 登录功能（账号为手机号或邮箱）
     * @param phone 手机号
     * @param email 邮箱
     * @param password 密码
     * @return 布尔值
     */
    @Override
    public String login(String phone, String email, String password) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        //qw.eq("phone",phone).or().eq("email",email);
        qw.and(Wrapper -> Wrapper.eq("phone",phone).or().eq("email",email));
        qw.eq("password",password);
        System.out.println("打印phone："+phone);
        System.out.println("打印email："+email);
        System.out.println("打印password："+password);
        String a = "";
        if (baseMapper.selectList(qw).isEmpty()){
            a="登陆失败！！！";
        }else {
            a="登陆成功。。。";
        }
        return a;
    }
}
