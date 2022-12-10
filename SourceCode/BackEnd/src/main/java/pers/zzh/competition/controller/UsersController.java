package pers.zzh.competition.controller;

import com.pig4cloud.captcha.ArithmeticCaptcha;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.service.UsersService;
import pers.zzh.competition.utils.JwtUtils;
import pers.zzh.competition.vo.Result;
import pers.zzh.competition.vo.ResultCode;
import pers.zzh.competition.vo.params.LoginParam;
import pers.zzh.competition.vo.params.PageQuery;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 前端控制器 - users表的操作
 */
@RestController
@RequestMapping("/api")
public class UsersController {
    @Resource
    private UsersService service;

    /**
     * 查询users表的全部
     *
     * @return 查询的数据
     */
    @GetMapping("/users")
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
     * 登录功能
     *
     * @param loginParam 用户账号密码
     * @param session    session对象
     * @return 是否登录成功，成功返回该用户信息
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginParam loginParam, HttpSession session) {
        // 定义session存入的验证码
        String sessionCaptcha;
        // 异常处理，赋值，发生异常返回验证码错误信息
        try {
            sessionCaptcha = session.getAttribute(loginParam.getDatetime()).toString();
        } catch (Exception e) {
            return Result.failure(ResultCode.CAPTCHA_EXPIRE);
        }
        // 删除session存入的验证码
        session.removeAttribute(loginParam.getDatetime());
        // 获取用户输入的验证码
        String userCaptcha = loginParam.getCaptcha();
        // 判断session存入的验证码是否跟用户输入的一样(比较字母，忽略大小写.equalsIgnoreCase())
        if (sessionCaptcha.equalsIgnoreCase(userCaptcha)) {
            return service.selectPhoneEmailPassword(loginParam.getPhone()
                    , loginParam.getEmail()
                    , DigestUtils.md5DigestAsHex(loginParam.getPassword().getBytes()));
        }
        return Result.failure(ResultCode.CAPTCHA_ERROR);
    }

    /**
     * 自动登录
     *
     * @param loginParam 用户账号密码
     * @return 是否登录成功，成功返回该用户信息
     */
    @PostMapping("/automaticLogin")
    public Result automaticLogin(@RequestBody LoginParam loginParam) {
        return service.selectPhoneEmailPassword(loginParam.getPhone(), loginParam.getEmail(), loginParam.getPassword());
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
     * 生成验证码
     *
     * @param dateTime 时间戳
     * @param response 响应
     * @param session  会话
     */
    @GetMapping("/captcha")
    public void getCaptcha(@RequestParam("ulid") String ulid, HttpServletResponse response, HttpSession session) throws Exception {
        ServletOutputStream outputStream = response.getOutputStream();
        // 算数验证码,设置图片宽度、高度、几个数字进行运算
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(120, 40, 2);
        // 输出图片
        captcha.out(outputStream);
        // 获取运算的结果并打印
        System.out.println(ulid + " : " + captcha.text());
        // 将时间戳作为key，验证码作为value存入session
        session.setAttribute(ulid, captcha.text());
        // 设置session过期时间为60秒
        session.setMaxInactiveInterval(60);
    }

    /**
     * 查询手机号和邮箱是否存在
     *
     * @param phoneEmail 登录对象
     * @return 验证
     */
    @PostMapping("/registerVerify")
    public Result registerVerify(@RequestBody LoginParam phoneEmail) {
        return service.selectPhoneEmail(phoneEmail);
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

    /**
     * 查询单个用户信息
     *
     * @param uid 用户id
     * @return 统一返回对象
     */
    @GetMapping("/user/{uid}")
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
