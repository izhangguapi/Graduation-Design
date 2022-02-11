package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.zzh.competition.entity.Messages;
import pers.zzh.competition.mapper.MessagesMapper;
import pers.zzh.competition.service.MessagesService;

import java.util.List;


@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, Messages> implements MessagesService {

    // 首页查询公告
    @Override
    public List<Messages> selectAnnouncement() {
        QueryWrapper<Messages> qw = new QueryWrapper<>();
        qw.select("message_id,title").last("WHERE recipient=1 ORDER BY time DESC LIMIT 8");
        return baseMapper.selectList(qw);
    }
    // 分页查询公告
    @Override
    public Page<Messages> selectAnnouncementPage(int currentPage) {
        Page<Messages> page = new Page<>(currentPage, 10); // 创建分页对象
        QueryWrapper<Messages> qw = new QueryWrapper<>();
        qw.select("message_id,title,time").eq("recipient",1).orderByDesc("time");
        return baseMapper.selectPage(page,qw);
    }

    @Override
    public Messages selectAnnouncementById(int id) {
        return baseMapper.selectById(id);
    }
}
