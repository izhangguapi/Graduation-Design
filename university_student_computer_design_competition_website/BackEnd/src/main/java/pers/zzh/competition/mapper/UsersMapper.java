package pers.zzh.competition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import pers.zzh.competition.entity.Users;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersMapper extends BaseMapper<Users> {
    //多表联合查询，查出组名
    @Select("SELECT * FROM users inner join groups ON users.group_id = groups.group_id order by user_id")
    List<Map<String, Object>> selectAllUsersAndGroups(Page<Map<String,Object>> page);

}