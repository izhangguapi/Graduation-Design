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
     * @param contests
     * @return
     */
    @PostMapping("/addContests")
    public Result addContests(@RequestBody Contests contests) {
        return new Result(service.save(contests));
    }

    /**
     * 修改比赛
     *
     * @param contests
     * @return
     */
    @PutMapping("/updateContests")
    public Result updateContests(@RequestBody Contests contests) {
        return new Result( service.updateById(contests));
    }


    /**
     * 根据id查询一条数据
     *
     * @param id
     * @return
     */
    @GetMapping("/contests/{id}")
    public Result selectContestsOne(@PathVariable String id) {
        return new Result(200, service.selectContestsOne(id));
    }

    /**
     * 查询一些数据
     *
     * @param num
     * @return
     */
    @GetMapping("/contestsList/{num}")
    public Result selectContests(@PathVariable int num) {
//        return new Result( service.selectContests(num));
        return new         Result(200, "查询成功", service.selectContests(num));

    }

    /**
     * 搜索功能
     *
     * @param s
     * @return
     */
    @GetMapping("/search")
    public Result selectContests(@RequestParam("query") String query) {
        return new Result(service.selectContestsLike(query));
    }

    /**
     * 根据组id查询
     *
     * @param gid
     * @return
     */
    @GetMapping("/contests/gid")
    public Result selectContestsByGid(@RequestParam("gid") String gid) {
        return new Result(service.selectContestsByGid(gid));
    }
}
