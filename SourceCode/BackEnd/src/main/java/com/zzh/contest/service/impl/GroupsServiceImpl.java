package com.zzh.contest.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.zzh.contest.entity.Groups;
import com.zzh.contest.entity.Users;
import com.zzh.contest.entity.dto.PageQuery;
import com.zzh.contest.mapper.GroupsMapper;
import com.zzh.contest.service.GroupsService;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Service;

@Service
public class GroupsServiceImpl extends ServiceImpl<GroupsMapper, Groups> implements GroupsService {
    /**
     * 查询组
     *
     * @param encoding 组编码
     * @return 组
     */
    @Override
    public Groups selectEncoding(String encoding) {
        MPJLambdaWrapper<Groups> wrapper = new MPJLambdaWrapper<>();
        wrapper.eq(Groups::getEncoding, encoding).notIn(Groups::getGroupId, 1, 2);
        return baseMapper.selectOne(wrapper);
    }

    /**
     * 添加组并返回组id
     *
     * @param groups 对象
     * @return 组id
     */
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "groupId", keyColumn = "groupId")
    public Integer insertGroupGetId(Groups groups) {
        try {
            baseMapper.insert(groups);
            return groups.getGroupId();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public IPage<Groups> selectGroupsList(PageQuery pageQuery) {
        IPage<Groups> page = new Page<>(pageQuery.getCurrentPage(), pageQuery.getPageSize());
        MPJLambdaWrapper<Groups> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Groups.class)
                .leftJoin(Users.class, Users::getGroupId, Groups::getGroupId)
                .selectCount(Users::getUserId, "number");
        if (pageQuery.getQuery() != null) {
            wrapper.like(Groups::getGroupName, pageQuery.getQuery().trim())
                    .or()
                    .like(Groups::getEncoding, pageQuery.getQuery().trim());
        }
        wrapper.groupBy(Groups::getGroupId);
        // 查询全部，尾部添加链表条件
        // String sql = " LEFT JOIN users ON groups.group_id = users.group_id";
        // if (pageQuery.getQuery() != null) {
        //     sql += " WHERE CONCAT(group_name,encoding) LIKE '%" + pageQuery.getQuery().trim() + "%'";
        // }
        // sql += " GROUP BY groups.group_id";
        //
        // qw.select("groups.*,COUNT(user_id) number")
        //         .last(sql);
        return baseMapper.selectJoinPage(page, Groups.class, wrapper);
    }
}