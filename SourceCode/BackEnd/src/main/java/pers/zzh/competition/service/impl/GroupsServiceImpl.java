package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Service;
import pers.zzh.competition.entity.Groups;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.mapper.GroupsMapper;
import pers.zzh.competition.service.GroupsService;
import pers.zzh.competition.vo.params.PageQuery;

import java.util.List;

@Service
public class GroupsServiceImpl extends ServiceImpl<GroupsMapper, Groups> implements GroupsService {
    /**
     * 查询组
     *
     * @param encoding 组编码
     * @return 组
     */
    @Override
    public List<Groups> selectEncoding(String encoding) {
        QueryWrapper<Groups> qw = new QueryWrapper<>();
        qw.eq("encoding", encoding)
                .last(" AND group_id NOT IN ('1','2')");
        return baseMapper.selectList(qw);
    }

    /**
     * 添加组并返回id
     *
     * @param groups 对象
     * @return 组id
     */
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "groupId", keyColumn = "groupId")
    public int insertGroupGetId(Groups groups) {
        int num;
        try {
            baseMapper.insert(groups);
            num = groups.getGroupId();
        } catch (Exception e) {
            num = 0;
        }
        return num;
    }
    @Override
    public Page<Groups> selectGroupsList(PageQuery pageQuery) {
        // 创建分页对象
        Page<Groups> page = new Page<>(pageQuery.getCurrentPage(), pageQuery.getPageSize());
        // 创建条件构造器
        QueryWrapper<Groups> qw = new QueryWrapper<>();
        // 查询全部，尾部添加链表条件
        String sql = " LEFT JOIN users ON groups.group_id = users.group_id";
        if (pageQuery.getQuery()!=null){
            sql+=" WHERE CONCAT(group_name,encoding) LIKE '%" + pageQuery.getQuery().trim() + "%'";
        }
        sql+=" GROUP BY groups.group_id";

        qw.select("groups.*,COUNT(user_id) number")
                .last(sql);
        return baseMapper.selectPage(page, qw);
    }
}