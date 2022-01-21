package pers.zzh.competition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import pers.zzh.competition.entity.Groups;

@Mapper
public interface GroupsMapper extends BaseMapper<Groups> {
//    @Insert("INSERT INTO groups VALUES (DEFAULT,#{groupName},#{encoding})")
//    @Options(useGeneratedKeys = true,keyProperty = "groupId",keyColumn = "groupId")
//    int insertGroupGetId(Groups groups);
}
