package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.zzh.competition.entity.Contests;
import pers.zzh.competition.mapper.ContestsMapper;
import pers.zzh.competition.service.ContestsService;

import java.util.List;

@Service
public class ContestsServiceImpl extends ServiceImpl<ContestsMapper, Contests> implements ContestsService {
    // 添加比赛
    @Override
    public int insertContests(Contests contests) {
        int num;
        try {
            baseMapper.insert(contests);
            num = contests.getGroupId();
        } catch (Exception e) {
            num = 0;
        }
        return num;
    }

    /// 根据id查询一条数据
    @Override
    public Contests selectContestsOne(String id) {
        QueryWrapper<Contests> qw = new QueryWrapper<>();
        qw.select("contest_id,contest_text,contest_title,name,group_name,reg_start_time,start_time,reg_end_time,end_time,promulgator")
                .last(" INNER JOIN groups g ON contests.group_id=g.group_id\n" +
                        " INNER JOIN users u ON contests.promulgator=u.user_id\n" +
                        " WHERE contest_id=" + id);
        return baseMapper.selectOne(qw);
    }

    // 查询比赛数据
    @Override
    public List<Contests> selectContests(int num) {
        QueryWrapper<Contests> qw = new QueryWrapper<>();
        qw.select("contest_id,contest_title,url,reg_start_time,reg_end_time")
                .last(" c,users u WHERE c.promulgator=u.user_id LIMIT " + num + ",4");
        return baseMapper.selectList(qw);
    }

    // 搜索
    @Override
    public List<Contests> selectContestsLike(String s) {
        QueryWrapper<Contests> qw = new QueryWrapper<>();
        qw.select("contest_id,contest_title,reg_end_time")
                .like("contest_title", s).orderByDesc("reg_end_time");
        return baseMapper.selectList(qw);
    }
}
