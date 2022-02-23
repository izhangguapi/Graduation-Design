package pers.zzh.competition.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Scores;
import pers.zzh.competition.service.ScoresService;
import pers.zzh.competition.utils.Result;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScoresController {

    final ScoresService service;

    //报名
    @GetMapping("/addScores/{uid}/{cid}")
    public Result addScores(@PathVariable String uid,@PathVariable int cid) {
        return new Result(200, "验证成功", service.insertScores(uid,cid));
    }

    //查询是否已报名
    @GetMapping("/scores/{uid}/{cid}")
    public Result selectScores(@PathVariable int uid,@PathVariable int cid) {
        return new Result(200, "验证成功", service.selectScores(uid,cid));
    }

    //查询用户报名且评审完成的所有比赛
    @GetMapping("/scores/contest/{uid}")
    public Result selectForContest(@PathVariable int uid){
        return new Result(200, service.selectForContest(uid));
    }
    //查询用户报名且评审未完成的所有比赛
    @GetMapping("/scores/contestNot/{uid}")
    public Result selectForContestNot(@PathVariable int uid){
        return new Result(200, service.selectForContestNot(uid));
    }
    //查询比赛结果
    @GetMapping("/scores/{sid}")
    public Result selectScoresById(@PathVariable int sid){
        return new Result(200, service.selectScoresById(sid));
    }

    //查询该比赛报名人
    @GetMapping("/scores/users")
    public Result selectScoresByCid(@RequestParam("cid") String cid){
        return new Result(200, service.selectScoresByCid(cid));
    }

    @PutMapping("/scores/update")
    public Result upDate(@RequestBody Scores scores) {
        return new Result(service.updateById(scores));
    }
}
