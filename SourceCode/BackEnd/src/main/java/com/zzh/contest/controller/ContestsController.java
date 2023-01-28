package com.zzh.contest.controller;

import com.zzh.contest.entity.Contests;
import com.zzh.contest.service.ContestsService;
import com.zzh.contest.utils.result.Result;
import com.zzh.contest.utils.result.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 前端控制器 - contests表的操作
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/contests")
public class ContestsController {

    final ContestsService service;

    @GetMapping()
    public String hello() {
        return "hello";
    }

    @GetMapping("/latestContest")
    public Result latestContest() {
        return Result.success(ResultCode.ADD_SUCCESS, service.selectLatestContest());
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
     * @param cid 比赛id
     * @return 一条数据
     */
    @GetMapping("/contest/{cid}")
    public Result selectContestsByCID(@PathVariable String cid) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectContestsByCID(cid));
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
    @GetMapping("/contests/{gid}")
    public Result selectContestsByGid(@PathVariable("gid") String gid) {
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
