package com.zzh.competition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.competition.entity.Messages;

import java.util.List;

public interface MessagesService extends IService<Messages> {
    // 查询用户收到的消息
    List<Messages> selectMessagesByRecipient(int id);

    // 未读变已读
    void upDataMessagesStatus(Messages messages);

    // 删除已读消息
    Integer deleteMessageRead(String uid);

    // 添加消息
    List<Messages> selectScoresSpellMessages(Object obj);
}