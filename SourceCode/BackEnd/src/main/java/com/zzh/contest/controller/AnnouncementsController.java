package com.zzh.contest.controller;

import com.zzh.contest.entity.Announcements;
import com.zzh.contest.service.AnnouncementsService;
import com.zzh.contest.utils.result.Result;
import com.zzh.contest.utils.result.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AnnouncementsController {

    final AnnouncementsService service;

    /**
     * 首页查询公告
     *
     * @return
     */
    @GetMapping("/messages/announcement")
    public Result selectAnnouncement() {
        List<Announcements> list = service.selectAnnouncement();
        return list.isEmpty()
                ? Result.failure(ResultCode.SELECT_IS_EMPTY)
                : Result.success(ResultCode.SELECT_SUCCESS, list);
    }

    /**
     * 分页查询公告
     *
     * @param currentPage
     * @return
     */
    @GetMapping("/messages/announcementPage/{currentPage}")
    public Result selectAnnouncementPage(@PathVariable int currentPage) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectAnnouncementPage(currentPage));
    }

    /**
     * id查询公告
     *
     * @param id
     * @return
     */
    @GetMapping("/messages/announcement/{id}")
    public Result selectAnnouncementById(@PathVariable int id) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectAnnouncementById(id));
    }
}
