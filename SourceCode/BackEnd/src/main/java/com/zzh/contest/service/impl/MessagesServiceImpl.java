package com.zzh.contest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.contest.entity.Messages;
import com.zzh.contest.mapper.MessagesMapper;
import com.zzh.contest.service.MessagesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, Messages> implements MessagesService {

    // 查询用户收到的消息
    @Override
    public List<Messages> selectMessagesByRecipient(int id) {
        QueryWrapper<Messages> qw = new QueryWrapper<>();
        qw.select("message_id,title,time,status,`name`").last("INNER JOIN users ON messages.sender = users.user_id WHERE recipient = " + id + " ORDER BY status,time DESC");
        return baseMapper.selectList(qw);
    }

    // 未读变已读
    @Override
    public void upDataMessagesStatus(Messages messages) {
        baseMapper.updateById(messages);
    }

    /**
     * 删除已读消息
     *
     * @param uid 用户id
     * @return 删除结果
     */
    @Override
    public Integer deleteMessageRead(String uid) {
        QueryWrapper<Messages> qw = new QueryWrapper<>();
        qw.and(wrapper -> wrapper.eq("recipient", uid).eq("status", true));
        return baseMapper.delete(qw);
    }

    @Override
    public List<Messages> selectScoresSpellMessages(Object obj) {
        // ScoresService.selectScoresByCid(cid)
        return null;
    }
}
