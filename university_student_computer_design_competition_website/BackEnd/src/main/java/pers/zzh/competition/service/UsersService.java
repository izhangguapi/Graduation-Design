package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import pers.zzh.competition.entity.Users;

import java.util.Map;

/**
 * 业务接口
 * @author 张恣豪
 * @since 2022-1-10
 */
public interface UsersService extends IService<Users> {
    //分页链表查询    List<Map<String,Object>> orderUserList(Page<Map<String,Object>> page, String id);

    /**
     * 分页链表查询
     * @param currentPage 第几页
     * @param pageSize 分几页
     * @return page列表
     */
    Page<Map<String,Object>> selectListPage(int currentPage, int pageSize);


    /**
     * 登录功能
     * @param phone 手机号
     * @param email 邮箱
     * @param password 密码
     * @return 字符串
     */
    String login(String phone, String email, String password);
}
