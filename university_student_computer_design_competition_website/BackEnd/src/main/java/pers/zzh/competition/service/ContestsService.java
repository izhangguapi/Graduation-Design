package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

import pers.zzh.competition.entity.Contests;

public interface ContestsService extends IService<Contests> {

    // 发布比赛
    int insertContests(Contests contests);

    // 根据id查询一条数据
    Contests selectContestsOne(String id);

    // 查询一条比赛数据
    List<Contests> selectContests(int num);

    // 搜索
    List<Contests> selectContestsLike(String s);
    // 根据组id查询
    List<Contests>  selectContestsByGid(String gid);
}
