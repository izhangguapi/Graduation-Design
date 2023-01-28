package com.zzh.contest;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.zzh.contest.entity.Role;
import com.zzh.contest.entity.UserRole;
import com.zzh.contest.mapper.*;
import com.zzh.contest.service.RoleService;
import com.zzh.contest.utils.JwtUtils;
import com.zzh.contest.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private GroupsMapper groupsMapper;
    @Autowired
    private ScoresMapper scoresMapper;
    @Autowired
    private MessagesMapper messagesMapper;
    @Autowired
    private ContestsMapper contestsMapper;
    @Autowired
    private AnnouncementsMapper announcementsMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Resource
    RedisCache redisCache;

    RoleService roleService;
    RoleMapper roleMapper;


    @Test
    void passwordTest() {
        System.out.println(passwordEncoder.encode("123456"));
    }

    @Test
    void getUserRoleTest() {
        MPJLambdaWrapper<Role> wrapper = new MPJLambdaWrapper<>();
        wrapper.select(Role::getRoleName)
                .innerJoin(UserRole.class, UserRole::getRoleId, Role::getRoleId)
                .eq(UserRole::getUserId, 1);

        List<Role> roles = roleMapper.selectList(wrapper);
        for (Role role : roles) {
            System.out.println(role.toString());
        }

        // MPJLambdaWrapper<UserRoleRelation> wrapper = new MPJLambdaWrapper<>();
        // wrapper.eq(UserRoleRelation::getUserId, 1);
        // System.out.println(wrapper.getTargetSql());
        // System.out.println(roleMapper.selectList(wrapper).toString());
    }

    @Test
    void jwtTest() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzQ4MzAwNDQsInVpZCI6Mn0.QU0-njFTLn8I4HJ_2Imym43ZhAWS-BW-u1-UYl7BKv8";
        Claims claims = JwtUtils.checkToken(token);
        System.out.println(claims);
    }

    @Test
    void getCaptcha() {
        // 算数验证码,设置图片宽度、高度、几个数字进行运算

    }
}