package pers.zzh.competition.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Messages;
import pers.zzh.competition.entity.Scores;
import pers.zzh.competition.service.MessagesService;
import pers.zzh.competition.service.ScoresService;
import pers.zzh.competition.vo.Result;
import pers.zzh.competition.vo.ResultCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 前端控制器 - messages表的操作
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MessagesController {

    final MessagesService service;
    final ScoresService scoresService;

    /**
     * 新增消息
     *
     * @param messages
     * @return
     */
    @PostMapping("/messages/insert")
    public Result insertMessage(@RequestBody Messages messages) {
        return service.save(messages)
                ? Result.success(ResultCode.ADD_SUCCESS)
                : Result.failure(ResultCode.ADD_FAIL);
    }

    /**
     * 首页查询公告
     *
     * @return
     */
    @GetMapping("/messages/announcement")
    public Result selectAnnouncement() {
        List<Messages> list = service.selectAnnouncement();
        return list.isEmpty()
                ? Result.failure(ResultCode.SELECT_IS_EMPTY)
                : Result.success(ResultCode.SELECT_SUCCESS, list);
    }

    /**
     * 分页查询公告
     *
     * @param currentPage
     * @return
     */
    @GetMapping("/messages/announcementPage/{currentPage}")
    public Result selectAnnouncementPage(@PathVariable int currentPage) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectAnnouncementPage(currentPage));
    }

    /**
     * id查询公告
     *
     * @param id
     * @return
     */
    @GetMapping("/messages/announcement/{id}")
    public Result selectAnnouncementById(@PathVariable int id) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectAnnouncementById(id));
    }

    /**
     * 查询用户收到的消息
     *
     * @param id
     * @return
     */
    @GetMapping("/messages/recipient/{id}")
    public Result selectMessagesByRecipient(@PathVariable int id) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectMessagesByRecipient(id));
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
     * @param
     * @return
     */
    @PostMapping("/messages/save")
    public Result addMessages(@RequestBody HashMap<String, String> map) {
        // 查询出比赛报名的用户
        LambdaQueryWrapper<Scores> qw = new LambdaQueryWrapper<>();
        qw.select(Scores::getContestant).eq(Scores::getContestId,map.get("cid"));
        List<Scores> scoresList = scoresService.list(qw);
        // new一个messages列表
        List<Messages> messagesList = new ArrayList<>();
        // 把查出来的报名用户的id一个个传入messages对象，然后添加到messages列表
        for (Scores scores : scoresList) {
            // new一个messages对象
            Messages messages = new Messages();
            // 添加发送人id，标题，内容
            messages.setSender(Integer.valueOf(map.get("uid")));
            messages.setTitle(map.get("title"));
            messages.setText(map.get("text"));
            messages.setRecipient(Integer.parseInt(scores.getContestant()));
            messagesList.add(messages);
        }
        // 返回是否插入成功
        return service.saveBatch(messagesList) ? Result.success(ResultCode.ADD_SUCCESS):Result.failure(ResultCode.ADD_FAIL);
    }

    /**
     * 删除选中消息
     *
     * @param messages
     * @return
     */
    @DeleteMapping("/deleteMessage")
    public Result deleteMessage(@RequestBody List<Messages> messages) {
        return Result.success(ResultCode.DELETE_SUCCESS, service.removeBatchByIds(messages));
    }

    /**
     * 删除已读消息
     *
     * @param uid
     * @return
     */
    @GetMapping("/deleteMessageRead")
    public Result deleteMessageRead(@RequestParam("uid") String uid) {
        return Result.success(ResultCode.DELETE_SUCCESS, service.deleteMessageRead(uid));
    }
}
