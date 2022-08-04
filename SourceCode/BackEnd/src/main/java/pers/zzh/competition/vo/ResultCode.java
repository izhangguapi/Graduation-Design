package pers.zzh.competition.vo;

/**
 * Result枚举类
 *
 * @author 张恣豪
 */

public enum ResultCode {

    // 枚举类属性
    SELECT_SUCCESS(200,"查询成功"),
    DELETE_SUCCESS(200,"删除成功"),
    ADD_SUCCESS(200,"添加成功"),
    UPDATE_SUCCESS(200,"修改成功"),
    LOGIN_SUCCESS(200,"登录成功"),
    REGISTER_SUCCESS(200,"注册成功"),

    SELECT_IS_EMPTY(201,"查询结果为空"),

    SELECT_FAIL(201,"查询失败！"),
    ADD_FAIL(201,"添加失败！"),
    UPDATE_FAIL(201,"修改失败！"),
    DELETE_FAIL(201,"删除失败！"),
    LOGIN_FAIL(201,"登录失败，账号或密码错误！"),
    REGISTER_FAIL(201,"注册失败！"),


    CAPTCHA_EXPIRE(201, "验证码失效，请重新登录！"),
    CAPTCHA_ERROR(201, "验证码错误！"),
    PARAMS_ERROR(10001,"参数有误"),
    ACCOUNT_PWD_NOT_EXIST(10002,"用户名或密码不存在！"),
    TOKEN_ILLEGAL(10003,"token不合法"),
    ACCOUNT_EXIST(10004,"手机号或邮箱已存在！"),
    NO_PERMISSION(70001,"无访问权限"),
    SESSION_TIME_OUT(90001,"会话超时"),
    NO_LOGIN(90002,"未登录");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg){
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
