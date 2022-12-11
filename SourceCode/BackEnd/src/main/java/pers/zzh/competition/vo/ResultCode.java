package pers.zzh.competition.vo;

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
    LOGIN_FAIL(201, "登录失败，账号或密码错误！"),
    ACCOUNT_PWD_NOT_EXIST(10002, "用户名或密码不存在！"),
    REGISTER_SUCCESS(200, "注册成功"),
    REGISTER_FAIL(201, "注册失败！"),
    NO_LOGIN(90002, "未登录"),
    CAPTCHA_EXPIRE(201, "验证码失效，请重新登录！"),
    CAPTCHA_ERROR(201, "验证码错误！"),
    PARAMS_ERROR(10001, "参数有误"),
    TOKEN_ILLEGAL(10003, "token不合法"),
    ACCOUNT_EXIST(10004, "手机号或邮箱已存在！"),
    // 其他
    NO_PERMISSION(70001, "无访问权限"),
    SESSION_TIME_OUT(90001, "会话超时");

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
