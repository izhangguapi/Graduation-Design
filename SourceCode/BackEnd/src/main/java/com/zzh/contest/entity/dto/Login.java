package com.zzh.contest.entity.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zzh.contest.entity.Role;
import com.zzh.contest.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义登录实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Login implements UserDetails {
    private Users users;
    private String token;
    List<Role> roles;
    @JSONField(serialize = false)
    List<SimpleGrantedAuthority> authorities;

    public Login(Users users, List<Role> roles) {
        this.users = users;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        // 方法同下（已弃用）
        // List<SimpleGrantedAuthority> list = new ArrayList<>();
        // for (Role role : roles) {
        //     SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
        //     list.add(authority);
        // }
        // 将查询到的角色信息（Role对象）放入authorities集合
        List<String> list = roles.stream().map(Role::getRoleName).collect(Collectors.toList());
        authorities = list.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getPhone();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}