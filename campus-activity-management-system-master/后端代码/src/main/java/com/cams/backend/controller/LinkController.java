package com.cams.backend.controller;


import com.cams.backend.common.lang.Result;
import com.cams.backend.entity.Link;
import com.cams.backend.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cams
 * @since 2021-09-08
 */
@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    LinkService linkService;

    /**
     * 活动报名（传body）
     */
    @PostMapping("/signup")
    public Result signActivity(@RequestBody Link link) {
        return linkService.signActivity(link);
    }

    /**
     * 活动报名（传参数）
     */
    @PostMapping("/sign")
    public Result signupActivity(@RequestParam("aid")int aid,
                                 @RequestParam("uid")int uid) {
        return linkService.signupActivity(aid,uid);
    }

    /**
     * 退出活动
     * 方法实现
     */
    @DeleteMapping("/quit")
    public Result quitActivity(@RequestParam("aid")int aid,
                                 @RequestParam("uid")int uid) {
        return linkService.quitActivity(aid,uid);
    }

    /**
     * 查看活动成员名单
     */
    @GetMapping("/aid/{aid}")
    public Result joinerList(@PathVariable("aid")int aid){
        return linkService.joinerList(aid);
    };

    /**
     * 查看我的加入
     */
    @GetMapping("/uid/{uId}")
    public Result myJoinList(@PathVariable("uId")int uid){return linkService.myJoinList(uid);};




}
