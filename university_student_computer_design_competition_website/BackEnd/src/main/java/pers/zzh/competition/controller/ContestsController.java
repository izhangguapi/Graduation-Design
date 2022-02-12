package pers.zzh.competition.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Contests;
import pers.zzh.competition.service.ContestsService;
import pers.zzh.competition.utils.Result;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ContestsController {

    final ContestsService service;

    // 添加组并反回组id
    @PostMapping("/addContests")
    public Result addGroup(@RequestBody Contests contests) {
        int num = service.insertContests(contests);
        return num == 0 ? new Result(201, "发布失败！", false) : new Result(200, "发布成功。", num);
    }

    // 根据id查询一条数据
    @GetMapping("/contests/{id}")
    public Result selectContestsOne(@PathVariable String id){
        return new Result(200, service.selectContestsOne(id));
    }

    // 查询一些数据
    @GetMapping("/contestsList/{num}")
    public Result selectContests(@PathVariable int num){
        return new Result(200, service.selectContests(num));
    }



}
