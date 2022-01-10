package com.cams.backend.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountProfile implements Serializable {
    private Integer id;

    private String username;

    private String status;
}
