package pers.zzh.competition.vo.params;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 自定义登录实体类
 * @author 张恣豪
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginParam {
    private String phone;
    private String email;
    private String password;
    private String datetime;
    private String captcha;
}