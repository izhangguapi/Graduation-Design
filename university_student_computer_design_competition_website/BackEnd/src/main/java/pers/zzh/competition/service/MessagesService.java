package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.zzh.competition.entity.Messages;

import java.util.List;

public interface MessagesService extends IService<Messages> {
    // 首页查询公告
    List<Messages> selectAnnouncement();
    // 分页查询公告
    Page<Messages> selectAnnouncementPage(int currentPage);
    // 分页查询公告
    Messages selectAnnouncementById(int id);
}
