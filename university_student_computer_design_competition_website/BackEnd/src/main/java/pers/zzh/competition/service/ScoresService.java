package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.zzh.competition.entity.Contests;
import pers.zzh.competition.entity.Scores;

import java.util.List;

public interface ScoresService extends IService<Scores> {

    long selectScores(int uid, int cid);
    int insertScores(int uid,int cid);
    List<Scores> selectScoresForUserId(int uid);
}
