package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.annotation.JsonInclude;
import pers.zzh.competition.entity.Contests;
import pers.zzh.competition.entity.Users;

import java.util.List;

/**
 * @author 张恣豪
 */
public interface ContestsService extends IService<Contests> {
    /**
     * 根据id查询一条数据
     *
     * @param id
     * @return
     */
    Contests selectContestsOne(String id);

    /**
     * 分页查询比赛
     *
     * @param num 第几页
     * @return 分页查询结果
     */
    Page<Contests> selectContestsPage(Integer num);

    /**
     * 搜索
     *
     * @param query
     * @return
     */
    List<Contests> selectContestsLike(String query);

    /**
     * 根据组id查询
     *
     * @param gid
     * @return
     */
    List<Contests> selectContestsByGid(String gid);

    /**
     * 根据组id查询有哪些比赛以及报名人数
     * @param cid 比赛id
     * @return 列表
     */
    Contests selectByCid(Integer cid);

    /**
     * 查询未审核的比赛
     * @return 未审核比赛的列表
     */
     List<Contests> selectContests();

    /**
     * 搜索比赛
     * @param query
     * @return
     */
    List<Contests> searchContests(String query);

    /**
     * 最新比赛数据
     * @return
     */
    Contests selectLatestContest();
}
