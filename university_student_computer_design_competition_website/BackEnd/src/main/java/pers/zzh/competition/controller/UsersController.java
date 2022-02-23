package pers.zzh.competition.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wf.captcha.GifCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import pers.zzh.competition.common.entity.Login;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.service.UsersService;
import pers.zzh.competition.utils.Result;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;

/**
 * 前端控制器 - users表的操作
 *
 * @author 张恣豪
 * @since 2022-1-12
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsersController {

    final UsersService service;

    // 查询users表的全部
    @GetMapping("/users")
    public Result getAll() {
        return new Result(200, "查询成功", service.list());
    }

    // 分页链表查询users
    @GetMapping("/users/{currentPage}/{pageSize}")
    public Result getAll(@PathVariable int currentPage, @PathVariable int pageSize) {
        Page<Users> list = service.selectListPage(currentPage, pageSize);
        return list.getRecords().isEmpty()
                ? new Result(200, "查询失败", Collections.emptyMap())
                : new Result(200, "查询成功", service.selectListPage(currentPage, pageSize));
    }

    // 登录
    @PostMapping("/login")
    public Result login(@RequestBody Login login, HttpSession session) {
        // 定义session存入的验证码
        String sessionCaptcha;
        //异常处理，赋值，发生异常返回验证码错误信息
        try {
            sessionCaptcha = session.getAttribute(login.getDatetime()).toString();
        } catch (Exception e) {
            return new Result(201, "验证码已失效，请刷新！", Collections.emptyMap());
        }
        // 删除session存入的验证码
        session.removeAttribute(login.getDatetime());
        // 获取用户输入的验证码
        String userCaptcha = login.getCaptcha();
//        System.out.println("用户输入：" + login.getDatetime() + "-" + userCaptcha);
        // 判断session存入的验证码是否跟用户输入的一样(比较字母，忽略大小写.equalsIgnoreCase())
        if (sessionCaptcha.equalsIgnoreCase(userCaptcha)) {
            Users users = service.selectPhoneEmailPassword(login.getPhone(), login.getEmail(), login.getPassword());
            return users == null
                    ? new Result(201, "密码错误", Collections.emptyMap())
                    : new Result(200, "登录成功", users);
        } else {
            return new Result(201, "验证码错误", Collections.emptyMap());
        }
    }

    // 生成验证码
    @GetMapping("/captcha")
    public void getCaptcha(@RequestParam("dateTime") String dateTime, HttpServletResponse response, HttpSession session) throws Exception {
        ServletOutputStream outputStream = response.getOutputStream();
        // 算术验证码 数字加减乘除. 建议2位运算就行:captcha.setLen(2);
        // ArithmeticCaptcha captcha = new ArithmeticCaptcha(120, 40);
        // 中文验证码
        // ChineseCaptcha captcha = new ChineseCaptcha(120, 40);
        // 英文与数字验证码
        // SpecCaptcha captcha = new SpecCaptcha(120, 40);
        // 中文动态验证码
        // ChineseGifCaptcha captcha = new ChineseGifCaptcha(120, 40);
        // 几位数运算，默认是两位
        // 英文与数字动态验证码
        GifCaptcha captcha = new GifCaptcha(120, 40);
        // 设置验证码的位数
        captcha.setLen(4);
        // 输出图片
        captcha.out(outputStream);
        // 获取运算的结果并打印
        System.out.println("用户获取：" + dateTime + "-" + captcha.text());
        // 将时间戳作为key，验证码作为value存入session，设置过期时间为30秒
        session.setAttribute(dateTime, captcha.text());
        session.setMaxInactiveInterval(30);
    }
    // 查询手机号和邮箱是否存在
    @PostMapping("/registerVerify")
    public Result registerVerify(@RequestBody Login phoneEmail) {
        return service.selectPhoneEmail(phoneEmail)
                ? new Result(200, "验证成功", true)
                : new Result(201, "手机号或邮箱存在，请重新输入", false);
    }
    // 注册
    @PostMapping("/register")
    public Result register(@RequestBody Users users) {
        return new Result(service.insertUsers(users));
    }
    // 查询单个用户信息
    @GetMapping("/user/{uid}")
    public Result mine(@PathVariable int uid) {
        return new Result(service.selectById(uid));
    }
    // 修改个人资料
    @PutMapping("/user/update")
    public Result update(@RequestBody Users users) {
        return new Result(service.updateUser(users));
    }
}
