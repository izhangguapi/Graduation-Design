package pers.zzh.competition.controller;

import com.wf.captcha.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.common.entity.Login;

import pers.zzh.competition.service.UsersService;
import pers.zzh.competition.utils.Result;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 前端控制器 - users表的操作
 * @author 张恣豪
 * @since 2022-1-12
 */
@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UsersService usersService;

    // 查询users表的全部
    @GetMapping("/users")
    public Result getAll() {
        return new Result(200,"查询成功",usersService.list());
    }

    // 分页链表查询users
    @GetMapping("{currentPage}/{pageSize}")
    public Result getAll(@PathVariable int currentPage, @PathVariable int pageSize) {
        return new Result(200,"查询成功",usersService.selectListPage(currentPage, pageSize));
    }

    // 登录
    @PostMapping("/login")
    public Result login(@RequestBody Login login, HttpSession session) {
        // 定义session存入的验证码
        String sessionCaptcha;
        //异常处理，赋值，发生异常返回验证码错误信息
        try {
            sessionCaptcha = session.getAttribute(login.getDatetime()).toString();
        }catch (Exception e){
            System.out.println("验证码失效,请刷新！");
            return new Result(2002,"验证码已失效，请刷新！",null);
        }
        // 删除session存入的验证码
        session.removeAttribute(login.getDatetime());
        // 获取用户输入的验证码
        String userCaptcha = login.getCaptcha();

        System.out.println("用户输入："+login.getDatetime()+"-"+userCaptcha);
        // 判断session存入的验证码是否跟用户输入的一样(比较字母，忽略大小写.equalsIgnoreCase())
        if (sessionCaptcha.equalsIgnoreCase(userCaptcha)){
            return new Result(200,"登录成功成功",usersService.login(login.getPhone(), login.getEmail(),login.getPassword()));
        }else {
            System.out.println("验证码错误");
            return new Result(201,"验证码错误",null);
        }
    }
    // 生成验证码
    @GetMapping("/captcha")
    public void getCode(@RequestParam("dateTime") String dateTime,HttpServletResponse response, HttpSession session) throws Exception {
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
        System.out.println("用户获取："+dateTime+"-"+captcha.text());
        // 将时间戳作为key，验证码作为value存入session，设置过期时间为30秒
        session.setAttribute(dateTime, captcha.text());
        session.setMaxInactiveInterval(30);
    }

}
