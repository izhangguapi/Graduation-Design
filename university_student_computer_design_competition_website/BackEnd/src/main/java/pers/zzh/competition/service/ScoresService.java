package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.zzh.competition.entity.Scores;

import java.util.List;

public interface ScoresService extends IService<Scores> {

    long selectScores(int uid, int cid);
    int insertScores(String uid,int cid);
    List<Scores> selectForContest(int uid);
    List<Scores> selectForContestNot(int uid);
    Scores selectScoresForId(int sid);


}
