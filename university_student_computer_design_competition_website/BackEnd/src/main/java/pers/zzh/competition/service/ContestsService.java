package pers.zzh.competition.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Options;
import pers.zzh.competition.entity.Groups;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.zzh.competition.entity.Contests;

public interface ContestsService extends IService<Contests> {

    int insertContests(Contests contests);

    Contests selectContestsOne(String id);

    //查询一条比赛数据
    List<Contests> selectContests(int num);


}
