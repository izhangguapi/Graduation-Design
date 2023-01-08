package com.zzh.contest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.zzh.contest.entity.Contests;
import com.zzh.contest.entity.Groups;
import com.zzh.contest.entity.Scores;
import com.zzh.contest.entity.Users;
import com.zzh.contest.mapper.ContestsMapper;
import com.zzh.contest.service.ContestsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestsServiceImpl extends ServiceImpl<ContestsMapper, Contests> implements ContestsService {
    /**
     * 根据id查询一条数据
     *
     * @param cid 比赛id
     * @return 一条数据
     */
    @Override
    public Contests selectContestsByCID(String cid) {
        MPJLambdaWrapper<Contests> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Contests.class)
                .innerJoin(Groups.class, Groups::getGroupId, Contests::getGroupId)
                .select(Groups::getGroupName)
                .innerJoin(Users.class, Users::getUserId, Contests::getPromulgator)
                .select(Users::getName)
                .eq(Contests::getStatus, true).eq(Contests::getContestId, cid);
        return baseMapper.selectJoinOne(Contests.class, wrapper);
    }

    /**
     * 分页查询比赛
     *
     * @param num 第几页
     * @return 分页查询结果
     */
    @Override
    public Page<Contests> selectContestsPage(Integer num) {
        LambdaQueryWrapper<Contests> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Contests::getContestId, Contests::getContestTitle, Contests::getUrl, Contests::getRegStartTime, Contests::getRegEndTime)
                .eq(Contests::getStatus, true);
        return baseMapper.selectPage(new Page<>(num, 4), wrapper);
    }

    /**
     * 搜索
     *
     * @param query 搜索值
     * @return 搜索结果列表
     */
    @Override
    public List<Contests> selectContestsLike(String query) {
        LambdaQueryWrapper<Contests> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Contests::getContestId, Contests::getContestTitle, Contests::getRegEndTime)
                .like(Contests::getContestTitle, query)
                .orderByDesc(Contests::getRegEndTime);
        return baseMapper.selectList(wrapper);
    }

    /**
     * 根据组id查询
     *
     * @param gid 组id
     * @return 列表
     */
    @Override
    public List<Contests> selectContestsByGid(String gid) {
        MPJLambdaWrapper<Contests> wrapper = new MPJLambdaWrapper<>();
        wrapper.select(Contests::getContestId, Contests::getContestTitle, Contests::getStatus, Contests::getRegEndTime, Contests::getStatusText)
                .leftJoin(Scores.class, Scores::getContestId, Contests::getContestId)
                .selectCount(Scores::getScoresId, "number")
                .innerJoin(Users.class, Users::getUserId, Contests::getPromulgator)
                .select(Users::getName)
                .eq(Contests::getGroupId, gid)
                .groupBy(Contests::getContestId);
        return baseMapper.selectJoinList(Contests.class, wrapper);
    }

    @Override
    public Integer selectByCid(Integer cid) {
        MPJLambdaWrapper<Contests> wrapper = new MPJLambdaWrapper<>();
        wrapper.select(Scores::getScoresId)
                .leftJoin(Scores.class, Scores::getContestId, Contests::getContestId)
                .eq(Contests::getContestId, cid);
        return baseMapper.selectJoinCount(wrapper);
    }

    @Override
    public List<Contests> selectContests() {
        MPJLambdaWrapper<Contests> wrapper = new MPJLambdaWrapper<>();
        wrapper.select(Contests::getContestId, Contests::getContestTitle, Contests::getContestText, Contests::getUrl, Contests::getRegEndTime, Contests::getStatus, Contests::getStatusText)
                .innerJoin(Groups.class, Groups::getGroupId, Contests::getGroupId)
                .select(Groups::getGroupName)
                .innerJoin(Users.class, Users::getUserId, Contests::getPromulgator)
                .select(Users::getName)
                .orderByDesc(Contests::getContestId);
        // .last("INNER JOIN groups ON contests.group_id=groups.group_id\n" +
        //         " INNER JOIN users ON contests.promulgator = users.user_id\n" +
        //         " ORDER BY contest_id DESC");
        return baseMapper.selectJoinList(Contests.class, wrapper);
    }

    @Override
    public List<Contests> searchContests(String query) {
        MPJLambdaWrapper<Contests> wrapper = new MPJLambdaWrapper<>();
        // qw.select("contest_id,contest_title,contest_text,group_name,`name`,`status`,status_text")
        //         .last("INNER JOIN groups ON contests.group_id=groups.group_id\n" +
        //                 " INNER JOIN users ON contests.promulgator = users.user_id\n" +
        //                 " WHERE contest_title LIKE '%" + query + "%' ORDER BY contest_id DESC");
        wrapper.select(Contests::getContestId, Contests::getContestTitle, Contests::getContestText, Contests::getStatus, Contests::getStatusText)
                .innerJoin(Groups.class, Groups::getGroupId, Contests::getGroupId)
                .select(Groups::getGroupName)
                .innerJoin(Users.class, Users::getUserId, Contests::getPromulgator)
                .select(Users::getName)
                .like(Contests::getContestTitle, query)
                .orderByDesc(Contests::getContestId);
        return baseMapper.selectJoinList(Contests.class, wrapper);
    }

    @Override
    public Contests selectLatestContest() {
        LambdaQueryWrapper<Contests> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Contests::getContestId, Contests::getContestTitle, Contests::getRegStartTime, Contests::getRegEndTime, Contests::getStartTime, Contests::getEndTime)
                .eq(Contests::getStatus, true)
                .orderByDesc(Contests::getContestId)
                .last("LIMIT 1");
        return baseMapper.selectOne(wrapper);
    }
}
