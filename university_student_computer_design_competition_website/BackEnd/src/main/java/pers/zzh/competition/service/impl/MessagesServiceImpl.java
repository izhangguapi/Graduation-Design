package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.zzh.competition.entity.Messages;
import pers.zzh.competition.mapper.MessagesMapper;
import pers.zzh.competition.service.MessagesService;

import java.util.List;

/**
 * @author 张恣豪
 */
@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, Messages> implements MessagesService {

    // 新增消息
    @Override
    public int insertMessage(Messages messages) {
        return baseMapper.insert(messages);
    }

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
        qw.select("message_id,title,time").eq("recipient", 1).orderByDesc("time");
        return baseMapper.selectPage(page, qw);
    }

    // id查询公告
    @Override
    public Messages selectAnnouncementById(int id) {
        QueryWrapper<Messages> qw = new QueryWrapper<>();
        qw.select("recipient,title,text,time,sender,state,`name`").last("INNER JOIN users ON messages.sender = users.user_id WHERE message_id = " + id);
        return baseMapper.selectOne(qw);
    }

    // 查询用户收到的消息
    @Override
    public List<Messages> selectMessagesByRecipient(int id) {
        QueryWrapper<Messages> qw = new QueryWrapper<>();
        qw.select("message_id,title,time,state,`name`").last("INNER JOIN users ON messages.sender = users.user_id WHERE recipient = " + id + " ORDER BY state,time DESC");
        return baseMapper.selectList(qw);
    }

    // 未读变已读
    @Override
    public void upDataMessagesState(Messages messages) {
        baseMapper.updateById(messages);
    }

}
