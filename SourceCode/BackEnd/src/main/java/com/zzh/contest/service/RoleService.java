package com.zzh.contest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.contest.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
    /**
     * 根据uid查询用户角色
     *
     * @param uid 用户id
     * @return 角色列表
     */
    List<Role> getUserRole(int uid);

}
