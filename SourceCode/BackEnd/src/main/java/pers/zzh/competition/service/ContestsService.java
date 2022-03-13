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
     * @param gid 组id
     * @return 列表
     */
    List<Contests> selectByGid(Integer gid);

    /**
     * 查询未审核的比赛
     * @return 未审核比赛的列表
     */
     List<Contests> selectContests();

    List<Contests> searchContests(String query);
}
