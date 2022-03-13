package pers.zzh.competition.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Messages;
import pers.zzh.competition.service.MessagesService;
import pers.zzh.competition.utils.Result;

import java.util.Collections;
import java.util.List;

/**
 * @author 张恣豪
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MessagesController {

    final MessagesService service;

    /**
     * 新增消息
     *
     * @param messages
     * @return
     */
    @PostMapping("/messages/insert")
    public Result insertMessage(@RequestBody Messages messages) {
        return new Result(service.insertMessage(messages));
    }

    /**
     * 首页查询公告
     *
     * @return
     */
    @GetMapping("/messages/announcement")
    public Result selectAnnouncement() {
        List<Messages> list = service.selectAnnouncement();
        return list.isEmpty() ? new Result(100, "查询结果为空", Collections.emptyMap()) : new Result(100, "查询成功", list);
    }

    /**
     * 分页查询公告
     *
     * @param currentPage
     * @return
     */
    @GetMapping("/messages/announcementPage/{currentPage}")
    public Result selectAnnouncementPage(@PathVariable int currentPage) {
        return new Result(service.selectAnnouncementPage(currentPage));
    }

    /**
     * id查询公告
     *
     * @param id
     * @return
     */
    @GetMapping("/messages/announcement/{id}")
    public Result selectAnnouncementById(@PathVariable int id) {
        return new Result(service.selectAnnouncementById(id));
    }

    /**
     * 查询用户收到的消息
     *
     * @param id
     * @return
     */
    @GetMapping("/messages/recipient/{id}")
    public Result selectMessagesByRecipient(@PathVariable int id) {
        return new Result(service.selectMessagesByRecipient(id));
    }

    /**
     * 未读变已读
     *
     * @param messages
     */
    @PutMapping("/messages/status")
    public void upDataMessagesStatus(@RequestBody Messages messages) {
        service.updateById(messages);
    }

    /**
     * 发送消息
     *
     * @param messages
     * @return
     */
    @PostMapping("/messages/save")
    public Result addMessages(@RequestBody List<Messages> messages) {
        return new Result(service.saveBatch(messages));
    }

    /**
     * 删除选中消息
     *
     * @param messages
     * @return
     */
    @DeleteMapping("/deleteMessage")
    public Result deleteMessage(@RequestBody List<Messages> messages) {
        return new Result(service.removeBatchByIds(messages));
    }

    /**
     * 删除已读消息
     *
     * @param uid
     * @return
     */
    @GetMapping("/deleteMessageRead")
    public Result deleteMessageRead(@RequestParam("uid") String uid) {
        return new Result(service.deleteMessageRead(uid));
    }
}
