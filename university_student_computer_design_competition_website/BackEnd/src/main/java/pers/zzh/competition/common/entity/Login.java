package pers.zzh.competition.common.entity;

import lombok.Data;

@Data
public class Login {
    private String phone;
    private String email;
    private String password;
    private String datetime;
    private String captcha;
}