package com.zzh.contest.utils.result;

/**
 * 统一返回类的枚举类
 */
public enum ResultCode {
    // 增删改查
    SELECT_SUCCESS(200, "查询成功"),
    SELECT_FAIL(201, "查询失败！"),
    DELETE_SUCCESS(200, "删除成功"),
    DELETE_FAIL(201, "删除失败！"),
    ADD_SUCCESS(200, "添加成功"),
    ADD_FAIL(201, "添加失败！"),
    UPDATE_SUCCESS(200, "修改成功"),
    UPDATE_FAIL(201, "修改失败！"),
    SELECT_IS_EMPTY(201, "查询结果为空"),
    // 登录注册相关
    LOGIN_SUCCESS(200, "登录成功"),
    LOGOUT_SUCCESS(200, "退出成功"),
    LOGIN_FAIL(301, "账号或密码错误，请重新登录"),
    NO_LOGIN(401, "您还未登录"),
    LOGIN_EXPIRE(401, "登录信息已过期"),
    AUTHENTICATION_FAIL(403, "您无权访问"),
    ACCOUNT_PWD_NOT_EXIST(10002, "用户名或密码不存在"),
    REGISTER_SUCCESS(200, "注册成功"),
    REGISTER_FAIL(201, "注册失败！"),
    NO_CAPTCHA(301, "验证码不能为空，请输入验证码"),
    CAPTCHA_ERROR_OR_EXPIRE(302, "验证码输入错误或已失效，请重试"),
    CAPTCHA_ERROR(303, "验证码错误！"),
    PARAMS_ERROR(10001, "参数有误"),
    TOKEN_ILLEGAL(10003, "token不合法"),
    ACCOUNT_EXIST(10004, "手机号或邮箱已存在"),
    // 其他
    NO_PERMISSION(70001, "无访问权限"),
    NO_TOKEN(70001, "没有获取到token"), LOGOUT_FAIL(2003, "退出登录失败");

    private final Integer code;
    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
