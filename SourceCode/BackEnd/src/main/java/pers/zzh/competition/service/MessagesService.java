package pers.zzh.competition.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.zzh.competition.entity.Messages;

import java.util.List;

/**
 * @author 张恣豪
 */
public interface MessagesService extends IService<Messages> {
    /**
     * 新增消息
     *
     * @param messages
     * @return
     */
    int insertMessage(Messages messages);

    /**
     * 首页查询公告
     *
     * @return
     */
    List<Messages> selectAnnouncement();

    /**
     * 分页查询公告
     *
     * @param currentPage
     * @return
     */
    Page<Messages> selectAnnouncementPage(int currentPage);

    /**
     * id查询公告
     *
     * @param id
     * @return
     */
    Messages selectAnnouncementById(int id);

    /**
     * 查询用户收到的消息
     *
     * @param id
     * @return
     */
    List<Messages> selectMessagesByRecipient(int id);

    /**
     * 未读变已读
     *
     * @param messages
     */
    void upDataMessagesStatus(Messages messages);

    /**
     * 删除已读消息
     * @param uid
     * @return
     */
    Integer deleteMessageRead(String uid);
}
