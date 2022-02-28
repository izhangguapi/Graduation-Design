package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

import pers.zzh.competition.entity.Contests;

/**
 * @author 张恣豪
 */
public interface ContestsService extends IService<Contests> {

    /**
     * 发布比赛
     *
     * @param contests
     * @return
     */
    int insertContests(Contests contests);

    /**
     * 根据id查询一条数据
     *
     * @param id
     * @return
     */
    Contests selectContestsOne(String id);

    /**
     * 查询一条比赛数据
     *
     * @param num
     * @return
     */
    List<Contests> selectContests(int num);

    /**
     * 搜索
     *
     * @param s
     * @return
     */
    List<Contests> selectContestsLike(String s);

    /**
     * 根据组id查询
     *
     * @param gid
     * @return
     */
    List<Contests> selectContestsByGid(String gid);
}
