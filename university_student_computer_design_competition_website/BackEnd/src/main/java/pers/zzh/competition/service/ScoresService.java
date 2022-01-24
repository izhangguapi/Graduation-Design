package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.zzh.competition.entity.Scores;

public interface ScoresService extends IService<Scores> {

    int insertScores(int uid,int cid);
}
