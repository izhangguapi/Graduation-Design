package pers.zzh.competition.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Contests;
import pers.zzh.competition.service.ContestsService;
import pers.zzh.competition.vo.Result;
import pers.zzh.competition.vo.ResultCode;

/**
 * @author 张恣豪
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ContestsController {

    final ContestsService service;


    @GetMapping("/latestContest")
    public Result latestContest() {
        return Result.success(ResultCode.ADD_SUCCESS,service.selectLatestContest());
    }
    /**
     * 添加比赛
     *
     * @param contests 对象
     * @return 生效数量
     */
    @PostMapping("/addContest")
    public Result addContest(@RequestBody Contests contests) {
        return service.save(contests) ? Result.success(ResultCode.ADD_SUCCESS) : Result.failure(ResultCode.ADD_FAIL);
    }

    /**
     * 修改比赛
     *
     * @param contests 对象
     * @return 生效数量
     */
    @PutMapping("/updateContest")
    public Result updateContest(@RequestBody Contests contests) {
        return service.updateById(contests) ? Result.success(ResultCode.UPDATE_SUCCESS) : Result.success(ResultCode.UPDATE_FAIL);

    }

    /**
     * 根据id查询一条数据，前台展示
     *
     * @param id 比赛id
     * @return 一条数据
     */
    @GetMapping("/contest/{id}")
    public Result selectContestOne(@PathVariable String id) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectContestsOne(id));
    }

    /**
     * 根据id查询一条数据，用于修改数据
     *
     * @param id 比赛id
     * @return 一条数据
     */
    @GetMapping("/contestForUpdate/{id}")
    public Result selectContestForUpdate(@PathVariable String id) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.getById(id));
    }

    /**
     * 分页查询比赛
     *
     * @param num 第几页
     * @return 分页查询结果
     */
    @GetMapping("/contestsList/{num}")
    public Result selectContestsPage(@PathVariable Integer num) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectContestsPage(num));
    }

    /**
     * 搜索功能
     *
     * @param query 查询内容
     * @return 查询结果
     */
    @GetMapping("/search")
    public Result selectContests(@RequestParam("query") String query) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectContestsLike(query));
    }

    /**
     * 根据组id查询比赛
     *
     * @param gid 组id
     * @return 查询结果
     */
    @GetMapping("/contests/gid")
    public Result selectContestsByGid(@RequestParam("gid") String gid) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectContestsByGid(gid));
    }

    /**
     * 删除一个比赛
     *
     * @param id 比赛di
     * @return 删除结果
     */
    @DeleteMapping("/deleteContest/{id}")
    public Result deleteContest(@PathVariable String id) {
        return service.removeById(id) ? Result.success(ResultCode.DELETE_SUCCESS) : Result.success(ResultCode.DELETE_FAIL);
    }

    @GetMapping("/selectContests")
    public Result selectContests() {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectContests());
    }
}
