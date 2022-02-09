package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import pers.zzh.competition.common.entity.Login;
import pers.zzh.competition.entity.Users;

import java.util.List;

/**
 * 业务接口
 * @author 张恣豪
 * @since 2022-1-10
 */
public interface UsersService extends IService<Users> {
    //分页链表查询
    Page<Users> selectListPage(int currentPage, int pageSize);
    //登录功能（账号为手机号或邮箱）
    List<Users> selectPhoneEmailPassword(String phone, String email, String password);
    // 注册
    int insertUsers(Users users);
    // 修改个人资料
    Boolean updateUser(Users users);
    // 查询手机号和邮箱是否存在
    Boolean selectPhoneEmail(Login phoneAndEmail);
    // 查询单个用户信息
    Users selectById(int uid);
}
