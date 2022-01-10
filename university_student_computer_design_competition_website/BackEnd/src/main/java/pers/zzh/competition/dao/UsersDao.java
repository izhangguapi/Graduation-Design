package pers.zzh.competition.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.zzh.competition.entities.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersDao extends BaseMapper<Users> {
    //多表联合查询，查出组名
    @Select("SELECT * FROM users inner join groups ON users.group_id = groups.group_id")
    List<Map<String, Object>> selectAllUser();


}