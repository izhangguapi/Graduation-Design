package com.zzh.contest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.zzh.contest.entity.Role;
import com.zzh.contest.entity.UserRole;
import com.zzh.contest.mapper.RoleMapper;
import com.zzh.contest.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public List<Role> getUserRole(int uid) {
        MPJLambdaWrapper<Role> wrapper = new MPJLambdaWrapper<>();
        wrapper.select(Role::getRoleName)
                .innerJoin(UserRole.class, UserRole::getRoleId, Role::getRoleId)
                .eq(UserRole::getUserId, uid);
        return baseMapper.selectList(wrapper);
    }
}