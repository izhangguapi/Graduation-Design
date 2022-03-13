package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.fasterxml.jackson.annotation.JsonInclude;
import pers.zzh.competition.common.entity.Login;
import pers.zzh.competition.entity.Users;

import java.util.List;


/**
 * 业务接口
 *
 * @author 张恣豪
 * @since 2022-1-10
 */
public interface UsersService extends IService<Users> {
    /**
     * 分页链表查询
     *
     * @param currentPage 当前第几页
     * @param pageSize    分页大小（分几页）
     * @return 结果对象
     */
    Page<Users> selectListPage(int currentPage, int pageSize);

    /**
     * 登录功能（账号为手机号或邮箱）
     *
     * @param phone    手机号
     * @param email    邮箱
     * @param password 密码
     * @return 登录结果
     */
    Users selectPhoneEmailPassword(String phone, String email, String password);

    /**
     * 注册
     *
     * @param users 对象
     * @return 用户id
     */
    int insertUsers(Users users);

    /**
     * 修改个人资料
     *
     * @param users 对象
     * @return true或false
     */
    Boolean updateUser(Users users);

    /**
     * 查询手机号和邮箱是否存在
     *
     * @param phoneAndEmail 登录信息的对象
     * @return true或false
     */
    Boolean selectPhoneEmail(Login phoneAndEmail);

    /**
     * 查询单个用户信息
     *
     * @param uid 用户id
     * @return 该id的用户信息
     */
    Users selectById(int uid);

    /**
     * 根据组id查询该组下有哪用户，生成一个用户id列表
     * @param gid 组id
     * @return 列表
     */
    List<Users> selectByGid(Integer gid);
}
