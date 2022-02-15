package pers.zzh.competition.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Messages;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.service.MessagesService;
import pers.zzh.competition.utils.Result;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MessagesController {

    final MessagesService service;

    // 新增消息
    @PostMapping("/messages/insert")
    public Result insertMessage(@RequestBody Messages messages) {
        return new Result(service.insertMessage(messages));
    }

    // 首页查询公告
    @GetMapping("/messages/announcement")
    public Result selectAnnouncement() {
        List<Messages> list = service.selectAnnouncement();
        return list.isEmpty() ? new Result(100, "查询结果为空", Collections.emptyMap()) : new Result(100, "查询成功", list);
    }

    // 分页查询公告
    @GetMapping("/messages/announcementPage/{currentPage}")
    public Result selectAnnouncementPage(@PathVariable int currentPage) {
        return new Result(service.selectAnnouncementPage(currentPage));
    }

    // id查询公告
    @GetMapping("/messages/announcement/{id}")
    public Result selectAnnouncementById(@PathVariable int id) {
        return new Result(service.selectAnnouncementById(id));
    }

    // 查询用户收到的消息
    @GetMapping("/messages/recipient/{id}")
    public Result selectMessagesByRecipient(@PathVariable int id) {
        return new Result(service.selectMessagesByRecipient(id));
    }

    // 未读变已读
    @PutMapping("/messages/state")
    public void upDataMessagesState(@RequestBody Messages messages) {
        service.upDataMessagesState(messages);
    }

    // 发送消息
    @PostMapping("/messages/save")
    public Result upDataMessagesSave(@RequestBody List<Messages> messages) {
        return new Result(service.saveBatch(messages));
    }
}
