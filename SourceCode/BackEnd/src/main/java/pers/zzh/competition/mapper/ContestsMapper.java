package pers.zzh.competition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.zzh.competition.entity.Contests;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 张恣豪
 */
@Mapper
public interface ContestsMapper extends BaseMapper<Contests> {
    /*
     *
    @Select("SELECT * FROM contests c,groups g,users u WHERE c.promulgator=u.user_id AND c.group_id=g.group_id AND contest_id=#{id}")
    Contests selectContestsID(String id);
    @Select("SELECT contest_id,contest_title,url,reg_start_time,reg_end_time FROM contests c,users u WHERE c.promulgator=u.user_id LIMIT #{num},4")
    List<Contests> selectContestsList(int num);
     */
}
