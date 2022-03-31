package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.zzh.competition.entity.Scores;

import java.util.List;

/**
 * @author 张恣豪
 */
public interface ScoresService extends IService<Scores> {
    /**
     * 查询组
     *
     * @param uid
     * @param cid
     * @return
     */
    long selectScores(int uid, int cid);

    /**
     * 添加比赛（报名）
     *
     * @param uid
     * @param cid
     * @return
     */
    int insertScores(String uid, int cid);

    /**
     * 查询用户报名且评审完成的所有比赛
     *
     * @param uid
     * @return
     */
    List<Scores> selectForContest(int uid);

    /**
     * 查询用户报名且评审未完成的所有比赛
     *
     * @param uid
     * @return
     */
    List<Scores> selectForContestNot(int uid);

    /**
     * 查询分数基本信息
     *
     * @param sid 评分报名id
     * @return
     */
    Scores selectScoresById(int sid);

    /**
     * 查询该比赛报名人
     *
     * @param cid 比赛id
     * @return
     */
    List<Scores> selectScoresByCid(String token,int cid);
}
