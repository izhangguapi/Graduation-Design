package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.service.IService;

import pers.zzh.competition.entity.Users;
import pers.zzh.competition.vo.Result;
import pers.zzh.competition.vo.params.LoginParam;

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
    Result selectListPage(int currentPage, int pageSize);

    /**
     * 登录功能（账号为手机号或邮箱）
     *
     * @param phone    手机号
     * @param email    邮箱
     * @param password 密码
     * @return 登录结果
     */
    Result selectPhoneEmailPassword(String phone, String email, String password);

    /**
     * 注册
     *
     * @param users 对象
     * @return 用户id
     */
    Result insertUsers(Users users);

    /**
     * 查询手机号和邮箱是否存在
     *
     * @param phoneAndEmail 登录信息的对象
     * @return true或false
     */
    Result selectPhoneEmail(LoginParam phoneAndEmail);

    /**
     * 查询单个用户信息
     *
     * @param uid 用户id
     * @return 该id的用户信息
     */
    Result selectById(int uid);

    /**
     * 根据组id查询该组下有哪用户，生成一个用户id列表
     * @param gid 组id
     * @return 列表
     */
    List<Users> selectByGid(Integer gid);
}
