package pers.zzh.competition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import pers.zzh.competition.entity.Contests;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContestsMapper extends BaseMapper<Contests> {
    @Select("SELECT * FROM contests c,groups g,users u WHERE c.promulgator=u.user_id AND c.group_id=g.group_id AND contest_id=#{id}")
    Contests selectID(String id);
}
