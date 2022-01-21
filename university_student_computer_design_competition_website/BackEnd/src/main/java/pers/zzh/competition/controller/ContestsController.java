package pers.zzh.competition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Contests;
import pers.zzh.competition.service.ContestsService;
import pers.zzh.competition.utils.Result;

@RestController
@RequestMapping("/api")
public class ContestsController {

    @Autowired
    private ContestsService contestsService;

    // 添加组并反回组id
    @PostMapping("/addContests")
    public Result addGroup(@RequestBody Contests contests) {
        int num = contestsService.insertContests(contests);
        return num == 0 ? new Result(201, "发布失败！", null) : new Result(200, "发布成功。", num);
    }

}
