package pers.zzh.competition.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Scores;
import pers.zzh.competition.service.ScoresService;
import pers.zzh.competition.utils.AverageAlgorithm;
import pers.zzh.competition.utils.FileOperations;
import pers.zzh.competition.utils.JwtUtils;
import pers.zzh.competition.vo.Result;
import pers.zzh.competition.vo.ResultCode;

import java.util.Map;


/**
 * 前端控制器 - scores表的操作
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScoresController {

    final ScoresService service;

    /**
     * 报名
     *
     * @param uid
     * @param cid
     * @return
     */
    @GetMapping("/addScores/{uid}/{cid}")
    public Result addScores(@PathVariable String uid, @PathVariable int cid) {
        return Result.success(ResultCode.ADD_SUCCESS, service.insertScores(uid, cid));
    }

    /**
     * 查询是否已报名
     *
     * @param uid
     * @param cid
     * @return
     */
    @GetMapping("/scores/{uid}/{cid}")
    public Result selectScores(@PathVariable int uid, @PathVariable int cid) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectScores(uid, cid));
    }

    /**
     * 查询用户报名且评审完成的所有比赛
     *
     * @param uid
     * @return
     */
    @GetMapping("/scores/contest/{uid}")
    public Result selectForContest(@PathVariable int uid) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectForContest(uid));
    }

    /**
     * 查询用户报名且评审未完成的所有比赛
     *
     * @param uid
     * @return
     */
    @GetMapping("/scores/contestNot/{uid}")
    public Result selectForContestNot(@PathVariable int uid) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectForContestNot(uid));
    }

    /**
     * 查询比赛结果
     *
     * @param sid 评分报名id
     * @return
     */
    @GetMapping("/scores/{sid}")
    public Result selectScoresById(@PathVariable int sid) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectScoresById(sid));
    }

    /**
     * 查询该比赛报名人
     *
     * @param cid 比赛id
     * @return
     */
    @GetMapping("/scores/users")
    public Result selectScoresByCid(@RequestHeader("Authorization") String token, @RequestParam("cid") int cid) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectScoresByCid(token,cid));
    }

    /**
     * 更新比赛表
     *
     * @param scores
     * @return
     */
    @PutMapping("/scores/update")
    public Result upDate(@RequestBody Scores scores) {
        return Result.success(ResultCode.ADD_SUCCESS, service.updateById(scores));
    }
}
