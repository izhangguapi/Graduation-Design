package com.zzh.contest.controller;

import com.zzh.contest.service.AnnouncementsService;
import com.zzh.contest.utils.result.Result;
import com.zzh.contest.utils.result.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnnouncementsController {

    final AnnouncementsService service;

    /**
     * 首页查询公告
     */
    @GetMapping("/announcement")
    public Result selectAnnouncement() {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectAnnouncement());
    }

    /**
     * 分页查询公告
     */
    @GetMapping("/announcement/page/{currentPage}")
    public Result selectAnnouncementPage(@PathVariable int currentPage) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectAnnouncementPage(currentPage));
    }

    /**
     * id查询公告
     */
    @GetMapping("/announcement/{id}")
    public Result selectAnnouncementById(@PathVariable int id) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectAnnouncementById(id));
    }
}
