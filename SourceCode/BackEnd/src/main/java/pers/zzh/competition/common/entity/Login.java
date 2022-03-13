package pers.zzh.competition.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 自定义登录实体类
 * @author 张恣豪
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Login {
    private String phone;
    private String email;
    private String password;
    private String datetime;
    private String captcha;
}