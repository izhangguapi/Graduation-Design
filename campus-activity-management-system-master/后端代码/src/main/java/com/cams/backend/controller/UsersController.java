package com.cams.backend.controller;


import com.cams.backend.common.lang.Result;
import com.cams.backend.entity.Users;
import com.cams.backend.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cams
 * @since 2021-09-06
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService userService;

    /**
     * 用户注册
     * 交互接口
     * @author cams
     */
    @PostMapping("/register")
    public Result register(@RequestBody Users user){
        return userService.register(user);

    }

    /**
     * 用户登录
     * 交互接口
     * @author cams
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody com.cams.backend.common.dto.LoginDto loginDto, HttpServletResponse response){
        return userService.login(loginDto,response);
    }

    /**
     * 用户登出
     * 交互接口
     */
 //   @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        return userService.logout();
    }

    /**
     * 查看个人信息
     * 函数声明
     * @author cams
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") int id){
        return userService.findById(id);
    }

    /**
     * 用户删除
     * 交互接口
     * @author cams
     */
    @DeleteMapping  ("/delete/{uid}")
    @ResponseBody
    public Result deleteUserById(@PathVariable("uid") int uid){
        return userService.deleteUserById(uid);
    }


    /**
     * 修改密码
     * 交互接口
     * @author cams
     */
 //   @RequiresAuthentication
    @PostMapping("/update")
    public Result updateUserInfo(@RequestBody Users user){
        return userService.updateUserInfo(user);

    }

    /**
     * 查看用户列表
     * 交互接口
     *
     * @author cams
     */
    @GetMapping("/allusers")
    public Result allUsers() {
        return userService.allUsers();
    }

}
