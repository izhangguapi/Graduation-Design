package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.mapper.UsersMapper;
import pers.zzh.competition.service.UsersService;
import pers.zzh.competition.utils.Jwt;
import pers.zzh.competition.vo.Result;
import pers.zzh.competition.vo.ResultCode;
import pers.zzh.competition.vo.params.LoginParam;

import java.util.List;

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
     *
     * @param currentPage 当前第几页
     * @param pageSize    分页大小（分几页）
     * @return 结果对象
     */
    @Override
    public Result selectListPage(int currentPage, int pageSize) {
        // 创建分页对象
        Page<Users> page = new Page<>(currentPage, pageSize);
        // 创建条件构造器
        QueryWrapper<Users> qw = new QueryWrapper<>();
        // 查询全部，尾部添加链表条件
        qw.select("*").last("inner join groups g ON users.group_id = g.group_id order by user_id");
        Page<Users> list = baseMapper.selectPage(page, qw);
        return list.getRecords().isEmpty()
                ? Result.fail(ResultCode.SELECT_FAIL)
                : Result.success(ResultCode.SELECT_SUCCESS, list);
    }

    /**
     * 登录功能（账号为手机号或邮箱）
     *
     * @param phone    手机号
     * @param email    邮箱
     * @param password 密码
     * @return 登录结果
     */
    @Override
    public Result selectPhoneEmailPassword(String phone, String email, String password) {
        LambdaQueryWrapper<Users> qw = new LambdaQueryWrapper<>();
        qw.select(Users::getUserId, Users::getName, Users::getGroupId, Users::getPassword)
                .eq(Users::getPhone, phone)
                .or()
                .eq(Users::getEmail, email)
                // 增加 查询效率
                .last("LIMIT 1");
        Users user = baseMapper.selectOne(qw);
        return user.getPassword().equals(password)
                ? Result.success(ResultCode.LOGIN_SUCCESS, Jwt.createToken(user.getName(), user.getUserId(), user.getGroupId(),user.getGroupId().equals(1)))
                : Result.fail(ResultCode.LOGIN_FAIL);
    }

    /**
     * 注册功能
     *
     * @param users 对象
     * @return 用户id
     */
    @Override
    public Result insertUsers(Users users) {
        users.setPassword(DigestUtils.md5DigestAsHex(users.getPassword().getBytes()));
        try {
            baseMapper.insert(users);
        } catch (Exception e) {
            return Result.fail(ResultCode.REGISTER_FAIL);
        }
        return Result.success(ResultCode.REGISTER_SUCCESS);
    }


    /**
     * 查询手机号和邮箱是否存在
     *
     * @param phoneAndEmail 登录对象
     * @return true或false
     */
    @Override
    public Result selectPhoneEmail(LoginParam phoneAndEmail) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.eq("phone", phoneAndEmail.getPhone())
                .or().eq("email", phoneAndEmail.getEmail());
        return baseMapper.selectList(qw).isEmpty()
                ? Result.success(ResultCode.SELECT_SUCCESS)
                : Result.fail(ResultCode.ACCOUNT_EXIST);
    }

    /**
     * 查询单个用户信息
     *
     * @param uid 用户id
     * @return 该id的用户信息
     */
    @Override
    public Result selectById(int uid) {
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.select("address,birthday,email,group_name,name,phone,school,sex")
                .last("inner join groups g ON users.group_id = g.group_id where user_id = " + uid);
        return Result.success(ResultCode.SELECT_SUCCESS, baseMapper.selectOne(qw));
    }

    @Override
    public List<Users> selectByGid(Integer gid) {
        // 根据组id查询该组下有哪用户，生成一个用户id列表
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.select("user_id").eq("group_id", gid);
        return baseMapper.selectList(qw);
    }


//    public Result findUserByToken(String token) {
//        if (StringUtils.isBlank(token)) {
//            return Result.fail(ResultCode.TOKEN_ILLEGAL);
//        }
//
//        return null;
//    }
}
