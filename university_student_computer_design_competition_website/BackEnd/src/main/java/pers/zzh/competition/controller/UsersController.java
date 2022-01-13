package pers.zzh.competition.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.service.UsersService;


import java.util.List;
import java.util.Map;

/**
 * 前端控制器 - users表的操作
 * @author 张恣豪
 * @since 2022-1-12
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<Users> getAll() {
        return usersService.list();
    }

    @GetMapping("{currentPage}/{pageSize}")
    public Page<Map<String, Object>> getAll(@PathVariable int currentPage, @PathVariable int pageSize) {
        return usersService.selectListPage(currentPage, pageSize);
    }

    /**
     * 登录
     * @param users 类
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestBody Users users) {
        return usersService.login(users.getPhone(), users.getEmail(),users.getPassword());
    }

}
