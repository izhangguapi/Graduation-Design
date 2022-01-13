package pers.zzh.competition.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.zzh.competition.entity.Contests;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContestsDao extends BaseMapper<Contests> {
}
