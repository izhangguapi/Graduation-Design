package pers.zzh.competition.controller;

import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Contests;
import pers.zzh.competition.service.ContestsService;
import pers.zzh.competition.utils.Result;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class ContestsController {

    @Resource
    private ContestsService contestsService;

    // 添加组并反回组id
    @PostMapping("/addContests")
    public Result addGroup(@RequestBody Contests contests) {
        int num = contestsService.insertContests(contests);
        return num == 0 ? new Result(201, "发布失败！", null) : new Result(200, "发布成功。", num);
    }

    @GetMapping("/Contests/{id}")
    public Result selectContestsOne(@PathVariable String id){
        return new Result(200, contestsService.selectContestsOne(id));
    }

    @GetMapping("/Contests")
    public Result selectContests(){
        return new Result(200, contestsService.selectContests());
    }
}
