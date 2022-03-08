package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.zzh.competition.entity.Contests;

import java.util.List;

/**
 * @author 张恣豪
 */
public interface ContestsService extends IService<Contests> {
    /**
     * 根据id查询一条数据
     *
     * @param id
     * @param status
     * @return
     */
    Contests selectContestsOne(String id);

    /**
     * 分页查询比赛
     *
     * @param num 第几页
     * @return 分页查询结果
     */
    Page<Contests> selectContests(Integer num);

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
