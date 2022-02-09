package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.zzh.competition.entity.Scores;
import pers.zzh.competition.mapper.ScoresMapper;
import pers.zzh.competition.service.ScoresService;

import java.util.List;


@Service
public class ScoresServiceImpl extends ServiceImpl<ScoresMapper, Scores> implements ScoresService {

    // 查询组
    @Override
    public long selectScores(int uid, int cid) {
        QueryWrapper<Scores> qw = new QueryWrapper<>();
        qw.eq("contest_id",cid).eq("contestant",uid);
        return baseMapper.selectCount(qw);
    }

    @Override
    public int insertScores(int uid, int cid) {
        Scores scores = new Scores();
        scores.setContestId(cid);
        scores.setContestant(uid);
        return baseMapper.insert(scores);
    }

    //查询
    @Override
    public List<Scores> selectScoresForUserId(int uid) {
        QueryWrapper<Scores> qw = new QueryWrapper<>(); // 创建条件构造器
        qw.select("scores_id,contest_title").last("inner join `contests` ON scores.contest_id = contests.contest_id WHERE contestant = "+uid);
        return baseMapper.selectList(qw);
    }
}
