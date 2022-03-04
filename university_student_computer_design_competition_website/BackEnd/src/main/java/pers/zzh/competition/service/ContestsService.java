package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

import pers.zzh.competition.entity.Contests;

/**
 * @author 张恣豪
 */
public interface ContestsService extends IService<Contests> {
    /**
     * 根据id查询一条数据
     *
     * @param id 比赛id
     * @return 一条数据
     */
    Contests selectContestsOne(String id);

    /**
     * 分页查询比赛
     *
     * @param num 第几页
     * @return 分页查询结果
     */
    Page<Contests> selectContests(int num);

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
}
