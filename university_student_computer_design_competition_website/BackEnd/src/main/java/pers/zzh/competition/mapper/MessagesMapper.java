package pers.zzh.competition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.zzh.competition.entity.Messages;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessagesMapper extends BaseMapper<Messages> {
    // 批量插入
    boolean insertBatch(List<Messages> entityList);
}
