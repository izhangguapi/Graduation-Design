package pers.zzh.competition.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.service.ScoresService;
import pers.zzh.competition.utils.Result;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScoresController {

    final ScoresService service;

    //报名
    @GetMapping("/addScores/{uid}/{cid}")
    public Result addScores(@PathVariable int uid,@PathVariable int cid) {
        return new Result(200, "验证成功", service.insertScores(uid,cid));
    }

    //查询是否已报名
    @GetMapping("/Scores/{uid}/{cid}")
    public Result selectScores(@PathVariable int uid,@PathVariable int cid) {
        return new Result(200, "验证成功", service.selectScores(uid,cid));
    }

    //查询用户报名的所有比赛
    @GetMapping("/contests/user/{uid}")
    public Result selectContestsForUserId(@PathVariable int uid){
        return new Result(200, service.selectScoresForUserId(uid));
    }
}
