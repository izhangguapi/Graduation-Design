package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.zzh.competition.entity.Contests;
import pers.zzh.competition.mapper.ContestsMapper;
import pers.zzh.competition.service.ContestsService;

import java.util.List;

/**
 * @author 张恣豪
 */
@Service
public class ContestsServiceImpl extends ServiceImpl<ContestsMapper, Contests> implements ContestsService {

    /**
     * 根据id查询一条数据
     * @param id 比赛id
     * @return 一条数据
     */
    @Override
    public Contests selectContestsOne(String id) {
        QueryWrapper<Contests> qw = new QueryWrapper<>();
        qw.select("contest_id,contest_text,contest_title,url,name,group_name,reg_start_time,start_time,reg_end_time,end_time,promulgator")
                .last(" INNER JOIN groups g ON contests.group_id=g.group_id\n" +
                        " INNER JOIN users u ON contests.promulgator=u.user_id\n" +
                        " WHERE contest_id=" + id);
        return baseMapper.selectOne(qw);
    }

    /**
     * 查询比赛数据
     * @param num 第几个开始
     * @return 列表
     */
    @Override
    public Page<Contests> selectContests(int num) {
        Page<Contests> page = new Page<>(num, 4);
        QueryWrapper<Contests> qw = new QueryWrapper<>();
        qw.select("contest_id,contest_title,url,reg_start_time,reg_end_time")
                .last(" c,users u WHERE c.promulgator=u.user_id");
        return baseMapper.selectPage(page,qw);
    }

    /**
     * 搜索
     * @param query 搜索值
     * @return 搜索结果列表
     */
    @Override
    public List<Contests> selectContestsLike(String query) {
        QueryWrapper<Contests> qw = new QueryWrapper<>();
        qw.select("contest_id,contest_title,reg_end_time")
                .like("contest_title", query).orderByDesc("reg_end_time");
        return baseMapper.selectList(qw);
    }

    /**
     * 根据组id查询
     * @param gid 组id
     * @return 列表
     */
    @Override
    public List<Contests> selectContestsByGid(String gid) {
        QueryWrapper<Contests> qw = new QueryWrapper<>();
        qw.select("contests.contest_id,contest_title,COUNT(scores_id) number")
                .last(" LEFT JOIN scores ON contests.contest_id=scores.contest_id\n" +
                        " WHERE group_id = "+gid+" GROUP BY contest_id");
        return baseMapper.selectList(qw);
    }
}
