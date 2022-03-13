package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.zzh.competition.entity.Scores;
import pers.zzh.competition.mapper.ScoresMapper;
import pers.zzh.competition.service.ScoresService;

import java.util.List;

/**
 * @author 张恣豪
 */
@Service
public class ScoresServiceImpl extends ServiceImpl<ScoresMapper, Scores> implements ScoresService {

    /**
     * 查询组
     *
     * @param uid
     * @param cid
     * @return
     */
    @Override
    public long selectScores(int uid, int cid) {
        QueryWrapper<Scores> qw = new QueryWrapper<>();
        qw.eq("contest_id", cid).eq("contestant", uid);
        return baseMapper.selectCount(qw);
    }

    /**
     * 添加比赛（报名）
     * @param uid
     * @param cid
     * @return
     */
    @Override
    public int insertScores(String uid, int cid) {
        Scores scores = new Scores();
        scores.setContestId(cid);
        scores.setContestant(uid);
        return baseMapper.insert(scores);
    }

    /**
     * 查询用户报名且评审完成的所有比赛
     * @param uid
     * @return
     */
    @Override
    public List<Scores> selectForContest(int uid) {
        QueryWrapper<Scores> qw = new QueryWrapper<>();
        qw.select("scores_id,contest_title")
                .last("inner join contests ON scores.contest_id = contests.contest_id WHERE result IS NOT NULL AND contestant = " + uid);
        return baseMapper.selectList(qw);
    }

    /**
     * 查询用户报名且评审未完成的所有比赛
     * @param uid
     * @return
     */
    @Override
    public List<Scores> selectForContestNot(int uid) {
        QueryWrapper<Scores> qw = new QueryWrapper<>();
        qw.select("contest_title").
                last("inner join contests ON scores.contest_id = contests.contest_id WHERE result IS NULL AND contestant = " + uid);
        return baseMapper.selectList(qw);
    }

    /**
     * 查询分数基本信息
     * @param sid
     * @return
     */
    @Override
    public Scores selectScoresById(int sid) {
        QueryWrapper<Scores> qw = new QueryWrapper<>();
        qw.select("scores_id,scores.contest_id,text,result,u1.`name` 'contestant',u2.`name` 'judge',c.contest_title")
                .last("INNER JOIN users u1 ON u1.user_id=scores.contestant\n" +
                        " INNER JOIN users u2 ON u2.user_id=scores.judge\n" +
                        " INNER JOIN contests c ON c.contest_id=scores.contest_id\n" +
                        " WHERE scores_id = " + sid);
        Scores scores = baseMapper.selectOne(qw);
        qw.clear();
        //查询比赛是否评审完成
        qw.select("count(*) ranking").isNull("result").eq("contest_id", scores.getContestId());
        if (baseMapper.selectOne(qw).getRanking() == 0) {
            qw.clear();
            //查询排名
            qw.select("count(result) ranking")
                    .last("scores where result > " + scores.getResult() + " AND contest_id = " + scores.getContestId());
            scores.setRanking(baseMapper.selectOne(qw).getRanking() + 1);
        }
        System.out.println(scores);
        return scores;
    }

    /**
     * 查询该比赛报名人
     * @param cid
     * @return
     */
    @Override
    public List<Scores> selectScoresByCid(String cid) {
        QueryWrapper<Scores> qw = new QueryWrapper<>();
        qw.select("scores_id,user_id,`name`,school,phone,status")
                .last("INNER JOIN users ON scores.contestant=users.user_id\n" +
                        "WHERE contest_id = " + cid + " ORDER BY status");
        return baseMapper.selectList(qw);
    }
}
