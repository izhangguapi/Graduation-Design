package pers.zzh.competition.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.zzh.competition.entities.Scores;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScoresDao extends BaseMapper<Scores> {
}
