package pers.zzh.competition.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.zzh.competition.entity.Groups;

/**
 * @author 张恣豪
 */
@Mapper
public interface GroupsMapper extends BaseMapper<Groups> {
    /*
    *
          @Insert("INSERT INTO groups VALUES (DEFAULT,#{groupName},#{encoding})")
    @Options(useGeneratedKeys = true,keyProperty = "groupId",keyColumn = "groupId")
    int insertGroupGetId(Groups groups);

     */

}
