package com.zzh.competition.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.competition.entity.Announcements;

import java.util.List;

public interface AnnouncementsService extends IService<Announcements> {
    // 首页查询公告
    List<Announcements> selectAnnouncement();

    // 分页查询公告
    Page<Announcements> selectAnnouncementPage(int currentPage);

    // id查询公告
    Announcements selectAnnouncementById(int id);
}