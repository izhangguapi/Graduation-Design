package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.zzh.competition.entity.Scores;
import pers.zzh.competition.mapper.ScoresMapper;
import pers.zzh.competition.service.ScoresService;


@Service
public class ScoresServiceImpl extends ServiceImpl<ScoresMapper, Scores> implements ScoresService {


    @Override
    public int insertScores(int uid, int cid) {
        QueryWrapper<Scores> qw = new QueryWrapper<>();
        qw.eq("contest_id",cid).eq("contestant",uid);
        if (baseMapper.selectCount(qw) == 0){
            Scores scores = new Scores();
            scores.setContestId(cid);
            scores.setContestant(uid);
            return baseMapper.insert(scores);
        }
        return 0;
    }
}
