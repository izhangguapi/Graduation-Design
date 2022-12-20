package com.zzh.competition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzh.competition.entity.Announcements;
import com.zzh.competition.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private GroupsMapper groupsMapper;
    @Autowired
    private ScoresMapper scoresMapper;
    @Autowired
    private MessagesMapper messagesMapper;
    @Autowired
    private ContestsMapper contestsMapper;
    @Autowired
    private AnnouncementsMapper announcementsMapper;

    @Test
    void Test() {
        LambdaQueryWrapper<Announcements> wrapper = new LambdaQueryWrapper<>();
        // qw.select("message_id,title").last("WHERE recipient=1 ORDER BY time DESC LIMIT 8");
        wrapper.select(Announcements::getMessageId, Announcements::getTitle)
                .orderByDesc(Announcements::getTime)
                .last("LIMIT 8");
        System.out.println(announcementsMapper.selectList(wrapper));
    }
}