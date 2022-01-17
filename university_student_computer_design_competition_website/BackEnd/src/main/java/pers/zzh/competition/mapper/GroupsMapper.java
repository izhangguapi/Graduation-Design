package pers.zzh.competition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pers.zzh.competition.entity.Groups;

import java.util.List;
import java.util.Map;

@Mapper
public interface GroupsMapper extends BaseMapper<Groups> {

}
