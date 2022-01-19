package pers.zzh.competition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import pers.zzh.competition.entity.Groups;

@Mapper
public interface GroupsMapper extends BaseMapper<Groups> {
}
