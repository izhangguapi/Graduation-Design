package pers.zzh.competition.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Contests;
import pers.zzh.competition.service.ContestsService;
import pers.zzh.competition.utils.Result;

/**
 * @author 张恣豪
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ContestsController {

    final ContestsService service;

    /**
     * 添加比赛
     *
     * @param contests 对象
     * @return 生效数量
     */
    @PostMapping("/addContest")
    public Result addContest(@RequestBody Contests contests) {
        return new Result(service.save(contests));
    }

    /**
     * 修改比赛
     *
     * @param contests 对象
     * @return 生效数量
     */
    @PutMapping("/updateContest")
    public Result updateContest(@RequestBody Contests contests) {
        return new Result(service.updateById(contests));
    }

    /**
     * 根据id查询一条数据，前端展示
     *
     * @param id 比赛id
     * @return 一条数据
     */
    @GetMapping("/contest/{id}")
    public Result selectContestOne(@PathVariable String id) {
        return new Result(200, service.selectContestsOne(id));
    }

    /**
     * 根据id查询一条数据
     *
     * @param id 比赛id
     * @return 一条数据
     */
    @GetMapping("/contestForUpdate/{id}")
    public Result selectContestForUpdate(@PathVariable String id) {
        return new Result(200, service.getById(id));
    }

    /**
     * 分页查询比赛
     *
     * @param num 第几页
     * @return 分页查询结果
     */
    @GetMapping("/contestsList/{num}")
    public Result selectContests(@PathVariable Integer num) {
        return new Result(service.selectContests(num));

    }

    /**
     * 搜索功能
     *
     * @param query 查询内容
     * @return 查询结果
     */
    @GetMapping("/search")
    public Result selectContests(@RequestParam("query") String query) {
        return new Result(service.selectContestsLike(query));
    }

    /**
     * 根据组id查询比赛
     *
     * @param gid 组id
     * @return 查询结果
     */
    @GetMapping("/contests/gid")
    public Result selectContestsByGid(@RequestParam("gid") String gid) {
        return new Result(service.selectContestsByGid(gid));
    }

    /**
     * 删除一个比赛
     *
     * @param id 比赛di
     * @return 删除结果
     */
    @DeleteMapping("/deleteContest/{id}")
    public Result deleteContest(@PathVariable String id) {
        return new Result(service.removeById(id));
    }
}
