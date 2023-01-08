package com.zzh.contest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.contest.entity.Scores;

import java.util.List;

public interface ScoresService extends IService<Scores> {
    // 查询组
    long selectScores(int uid, int cid);

    /**
     * 添加比赛（报名）
     *
     * @param uid 用户id
     * @param cid 比赛id
     * @return 添加结果
     */
    int insertScores(String uid, int cid);

    /**
     * 查询用户报名且评审完成的所有比赛
     *
     * @param uid 用户id
     * @return Scores列表
     */
    List<Scores> selectForContest(int uid);

    /**
     * 查询用户报名且评审未完成的所有比赛
     *
     * @param uid 用户id
     * @return Scores列表
     */
    List<Scores> selectForContestNot(int uid);

    /**
     * 查询分数基本信息
     *
     * @param sid 评分报名id
     * @return Scores对象
     */
    Scores selectScoresById(int sid);

    /**
     * 查询该比赛报名人
     *
     * @param cid 比赛id
     * @return Scores列表
     */
    List<Scores> selectScoresByCid(String token, int cid);
}
