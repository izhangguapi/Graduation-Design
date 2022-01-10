package com.cams.backend.service;

import com.cams.backend.common.lang.Result;
import com.cams.backend.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  业务层
 * </p>
 *
 * @author cams
 */
public interface UsersService extends IService<Users> {
    /**
     * 用户注册
     * 函数声明
     * @author cams
     */
    Result register(Users user);

    /**
     * 用户登录
     * 函数声明
     * @author cams
     */
    Result login(com.cams.backend.common.dto.LoginDto loginDto,
                 HttpServletResponse response);

    /**
     * 用户登出
     * 函数声明
     */
    public Result logout() ;


    /**
     * 根据id查找用户
     * 函数声明
     */
    Result findById(int id);

    Result deleteUserById(int id);

    /**
     * 修改密码
     * 函数声明
     */
    Result updateUserInfo(Users user);

    /**
     * 查看用户列表
     * 函数声明
     */
    Result allUsers();

}
