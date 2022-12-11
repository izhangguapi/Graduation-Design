package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.zzh.competition.entity.Groups;
import pers.zzh.competition.vo.params.PageQuery;

import java.util.List;

public interface GroupsService extends IService<Groups> {
    /**
     * 根据组编码查询除id为1，2，3以外的组
     *
     * @param encoding 组编码
     * @return Groups列表
     */
    List<Groups> selectEncoding(String encoding);

    /**
     * 添加组并返回id
     *
     * @param groups 用户组对象
     * @return 结果集
     */
    Integer insertGroupGetId(Groups groups);

    /**
     * 查询用户组表
     *
     * @param pageQuery 分页查询对象
     * @return 结果集
     */
    Page<Groups> selectGroupsList(PageQuery pageQuery);
}
