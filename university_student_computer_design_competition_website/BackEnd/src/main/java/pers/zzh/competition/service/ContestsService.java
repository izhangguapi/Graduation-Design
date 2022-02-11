package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import pers.zzh.competition.entity.Contests;

public interface ContestsService extends IService<Contests> {

    //发布比赛
    int insertContests(Contests contests);

    Contests selectContestsOne(String id);

    //查询一条比赛数据
    List<Contests> selectContests(int num);


}
