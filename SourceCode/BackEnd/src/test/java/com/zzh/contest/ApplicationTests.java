package com.zzh.contest;

import com.zzh.contest.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void Test() {
        String p1 = passwordEncoder.encode("123456");
        String p2 = passwordEncoder.encode("123456");
        System.out.println(p1);
        System.out.println(p2);
    }
}