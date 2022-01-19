package pers.zzh.competition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.zzh.competition.entity.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper extends BaseMapper<Users> {
}