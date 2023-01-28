package com.zzh.contest.controller;

import com.zzh.contest.entity.Users;
import com.zzh.contest.entity.dto.PageQuery;
import com.zzh.contest.service.UsersService;
import com.zzh.contest.utils.JwtUtils;
import com.zzh.contest.utils.result.Result;
import com.zzh.contest.utils.result.ResultCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 前端控制器 - users表的操作
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Resource
    private UsersService service;

    /**
     * 查询users表的全部
     *
     * @return 查询的数据
     */
    @GetMapping("/all")
    public Result getAll() {
        return Result.success(ResultCode.SELECT_SUCCESS, service.list());
    }

    /**
     * 分页链表查询users
     *
     * @param pageQuery 分页查询对象
     * @return 结果对象
     */
    @PostMapping("/users/list")
    public Result getAll(@RequestBody PageQuery pageQuery) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectListPage(pageQuery));
    }

    /**
     * token查询数据
     *
     * @param token token
     * @return token的body部分
     */
    @GetMapping("/currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token) {
        return Result.success(ResultCode.SELECT_SUCCESS, JwtUtils.checkToken(token));
    }

    /**
     * 查询单个用户信息
     *
     * @param uid 用户id
     * @return 统一返回对象
     */
    @GetMapping("/{uid}")
    @PreAuthorize("hasAuthority('admin')")
    public Result mine(@PathVariable int uid) {
        return service.selectById(uid);
    }

    /**
     * 修改个人资料
     *
     * @param users users对象
     * @return 是否成功
     */
    @PutMapping("/user/update")
    public Result update(@RequestBody Users users) {
        return service.updateById(users)
                ? Result.success(ResultCode.UPDATE_SUCCESS)
                : Result.failure(ResultCode.UPDATE_FAIL);
    }
}
