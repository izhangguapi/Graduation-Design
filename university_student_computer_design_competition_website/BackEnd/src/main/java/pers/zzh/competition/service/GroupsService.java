package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Options;
import pers.zzh.competition.entity.Groups;

import java.util.List;

/**
 * 业务接口
 * @author 张恣豪
 * @since 2022-1-17
 */
public interface GroupsService extends IService<Groups> {

    /**
     * 根据组编码查询除id为1，2，3以外的组
     * @param encoding 组编码
     * @return Groups列表
     */
    List<Groups> selectEncoding(String encoding);

    // 添加组并返回id
    @Options(useGeneratedKeys = true,keyProperty = "groupId",keyColumn = "groupId")
    int insertGroupGetId(Groups groups);

    Groups getGroupName(String id);
}
