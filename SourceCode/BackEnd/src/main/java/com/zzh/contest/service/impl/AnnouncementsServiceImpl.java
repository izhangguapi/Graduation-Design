package com.zzh.contest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.contest.entity.Announcements;
import com.zzh.contest.mapper.AnnouncementsMapper;
import com.zzh.contest.service.AnnouncementsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementsServiceImpl extends ServiceImpl<AnnouncementsMapper, Announcements> implements AnnouncementsService {
    // 首页查询公告
    @Override
    public List<Announcements> selectAnnouncement() {
        LambdaQueryWrapper<Announcements> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Announcements::getAnnouncementId, Announcements::getTitle)
                .orderByDesc(Announcements::getTime)
                .last("LIMIT 8");
        return baseMapper.selectList(wrapper);
    }

    // 分页查询公告
    @Override
    public Page<Announcements> selectAnnouncementPage(int currentPage) {
        LambdaQueryWrapper<Announcements> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Announcements::getAnnouncementId, Announcements::getTitle, Announcements::getTime)
                .orderByDesc(Announcements::getTime);
        return baseMapper.selectPage(new Page<>(currentPage, 10), wrapper);
    }

    // id查询公告
    @Override
    public Announcements selectAnnouncementById(int id) {
        return baseMapper.selectById(id);
    }
}
