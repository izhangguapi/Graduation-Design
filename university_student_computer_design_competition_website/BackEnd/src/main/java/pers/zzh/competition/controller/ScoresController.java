package pers.zzh.competition.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.service.ScoresService;
import pers.zzh.competition.utils.Result;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScoresController {

    final ScoresService scoresService;
    // 注册
    @GetMapping("/addScores/{uid}/{cid}")
    public Result addScores(@PathVariable int uid,@PathVariable int cid) {
        return new Result(200, "验证成功", scoresService.insertScores(uid,cid));
    }
}
