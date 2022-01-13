package pers.zzh.competition.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.zzh.competition.entity.Messages;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessagesDao extends BaseMapper<Messages> {
}
