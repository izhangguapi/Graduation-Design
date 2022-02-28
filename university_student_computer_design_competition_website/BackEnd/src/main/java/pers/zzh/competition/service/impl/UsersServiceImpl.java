package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.util.DigestUtils;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Service;
import pers.zzh.competition.common.entity.Login;
import pers.zzh.competition.mapper.UsersMapper;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.service.UsersService;

/**
 * 业务逻辑实现类
 *
 * @author 张恣豪
 * @since 2022-1-11
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    /**
     * 分页链表查询
     * @param currentPage 当前第几页
     * @param pageSize 分页大小（分几页）
     * @return 结果对象
     */
    @Override
    public Page<Users> selectListPage(int currentPage, int pageSize) {
        // 创建分页对象
        Page<Users> page = new Page<>(currentPage, pageSize);
        // 创建条件构造器
        QueryWrapper<Users> qw = new QueryWrapper<>();
        // 查询全部，尾部添加链表条件
        qw.select("*").last("inner join groups g ON users.group_id = g.group_id order by user_id");
        //返回结果
        return baseMapper.selectPage(page, qw);
    }

    /**
     * 登录功能（账号为手机号或邮箱）
     * @param phone    手机号
     * @param email    邮箱
     * @param password 密码
     * @return 登录结果
     */
    @Override
    public Users selectPhoneEmailPassword(String phone, String email, String password) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.select("user_id,name,group_id");
        qw.and(wrapper -> wrapper.eq("phone", phone).or().eq("email", email));
        qw.eq("password", password);
        return baseMapper.selectOne(qw);
    }

    /**
     * 注册功能
     * @param users 对象
     * @return 用户id
     */
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "groupId", keyColumn = "groupId")
    public int insertUsers(Users users) {
        users.setPassword(DigestUtils.md5DigestAsHex(users.getPassword().getBytes()));
        try {
            baseMapper.insert(users);
        } catch (Exception e) {
            return 0;
        }
        return users.getUserId();
    }

    /**
     * 修改个人资料
     * @param users users对象
     * @return 是否成功
     */
    @Override
    public Boolean updateUser(Users users) {
        try {
            if (baseMapper.updateById(users) != 0) {return true;}
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * 查询手机号和邮箱是否存在
     * @param phoneAndEmail 登录对象
     * @return true或false
     */
    @Override
    public Boolean selectPhoneEmail(Login phoneAndEmail) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.eq("phone", phoneAndEmail.getPhone())
                .or().eq("email", phoneAndEmail.getEmail());
        return baseMapper.selectList(qw).isEmpty();
    }

    /**
     * 查询单个用户信息
     * @param uid 用户id
     * @return 该id的用户信息
     */
    @Override
    public Users selectById(int uid) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.select("address,birthday,email,group_name,name,phone,school,sex")
                .last("inner join groups g ON users.group_id = g.group_id where user_id = " + uid);
        return baseMapper.selectOne(qw);
    }
}
