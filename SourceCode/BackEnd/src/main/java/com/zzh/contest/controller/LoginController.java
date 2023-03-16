package com.zzh.contest.controller;

import com.pig4cloud.captcha.ArithmeticCaptcha;
import com.zzh.contest.entity.Users;
import com.zzh.contest.service.UsersService;
import com.zzh.contest.utils.IpUtils;
import com.zzh.contest.utils.RedisCache;
import com.zzh.contest.utils.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {
    @Resource
    private UsersService service;

    @Resource
    RedisCache redisCache;

    @GetMapping("/ip")
    public String getIp(HttpServletRequest request) {
        return IpUtils.getIpAddr(request);
    }

    /**
     * 生成验证码
     *
     * @param response 响应
     */
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response, @RequestParam String ulid) throws Exception {
        // if (ulid.equals("")) {
        //     return;
        // }
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 算数验证码,设置图片宽度、高度、几个数字进行运算
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(120, 40);
        // 获取运算的公式：4-9+1=?
        System.out.println("计算结果为：" + captcha.getArithmeticString());
        // 获取运算的结果
        System.out.println("计算结果为：" + captcha.text());
        // 将ulid作为key，验证码作为value存入redis
        try {
            redisCache.setCacheObject(ulid, captcha.text(), 1, TimeUnit.MINUTES);
        } catch (Exception e) {
            System.out.println("存入Redis失败");
        } finally {
            // 输出验证码图片
            captcha.out(response.getOutputStream());
        }

    }

    /**
     * 登录
     *
     * @param users 用户账号密码
     * @return 是否登录成功，成功返回该用户信息
     */
    @PostMapping("/login")
    public Result login(@RequestBody Users users) {
        return service.login(users);
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result logout() {
        return service.logout();
    }

    /**
     * 自动登录
     *
     * @param users 用户账号密码
     * @return 是否登录成功，成功返回该用户信息
     */
    @PostMapping("/automaticLogin")
    public Result automaticLogin(@RequestBody Users users) {
        return service.login(users);
    }

    /**
     * 查询手机号和邮箱是否存在
     *
     * @param users 登录对象
     * @return 验证
     */
    @PostMapping("/registerVerify")
    public Result registerVerify(@RequestBody Users users) {
        return service.selectPhoneEmail(users);
    }

    /**
     * 注册
     *
     * @param users 对象
     * @return 统一返回对象
     */
    @PostMapping("/register")
    public Result register(@RequestBody Users users) {
        return service.insertUsers(users);
    }
}
