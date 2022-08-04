package pers.zzh.competition;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.zzh.competition.service.GroupsService;

@SpringBootTest
public class GroupsTest {
    @Autowired
    private GroupsService groupsService;
    @Test
    void Groups(){
        System.out.println(groupsService.selectEncoding("admin"));
    }
}
