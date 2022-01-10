package com.cams.backend.controller;


import com.cams.backend.common.lang.Result;
import com.cams.backend.entity.Activities;
import com.cams.backend.service.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cams
 * @since 2021-09-06
 */
@RestController
@RequestMapping("/activities")
public class ActivitiesController {


    @Autowired
    ActivitiesService activityService;


    /**
     * 发布新活动
     * 交互接口
     *
     * @author cams
     */
    @PostMapping("/newactivity")
    public Result newActivity(@RequestBody Activities activity) {
        return activityService.newActivity(activity);
    }


    /**
     * 查看活动列表
     * 交互接口
     *
     * @author cams
     */
    @GetMapping("/allactivities")
    public Result allActivities() {
        return activityService.allActivities();
    }

    /**
     * 查看未参与活动
     * 交互接口
     *
     * @author cams
     */
    @GetMapping("/unjoin/{uid}")
    public Result unjoinActivities(@PathVariable int uid) {
        return activityService.unjoinActivities(uid);
    }

    /**
     * 查看活动详情
     * 交互接口
     * @author cams
     */
    @GetMapping("/detail")
    public Result findByAid(@RequestParam("aid")int aid,
                            @RequestParam("uid")int uid){
        return activityService.findByAid(aid,uid);
    };

    /**
     * 删除活动
     * 交互接口
     * @author cams
     */
    @DeleteMapping("/delete")
    public Result deleteByAid(@RequestParam("aid")int aid,
                              @RequestParam("uid")int uid) {
        return activityService.deleteActivity(aid,uid);
    };

    /**
     * 根据活动类别查看活动
     * 交互接口
     * @author cams
     */
    @GetMapping("/sort/{aSort}")
    public Result sortActivities(@PathVariable("aSort")String asort){return activityService.sortActivities(asort);}

    /**
     * 查看我的发起
     * 交互接口
     * @author cams
     */
    @GetMapping("/my/{uid}")
    public Result myHostList(@PathVariable("uid")int uid){
        return activityService.myHostList(uid);
    };

    /**
     * 编辑活动
     * 交互接口
     *
     * @author cams
     */
    @PostMapping("/update")
    public Result updateActivity(@RequestBody Activities activity) {return activityService.updateActivity(activity);}


}
