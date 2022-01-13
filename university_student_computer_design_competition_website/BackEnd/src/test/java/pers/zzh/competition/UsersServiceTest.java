package pers.zzh.competition;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pers.zzh.competition.entity.Users;
import pers.zzh.competition.service.GroupsService;
import pers.zzh.competition.service.UsersService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UsersServiceTest {

    @Autowired
    private UsersService usersService;
    @Autowired
    private GroupsService groupsService;
    @Test
    void test(){
        //添加测试
        Users users = new Users();
        users.setName("测试");
        users.setPassword("这是密码");
        //usersService.registered(users);
        System.out.println(usersService.save(users));
    }
    @Test
    void test1(){
        //查询1条数据
        System.out.println(groupsService.getById(1));
    }
    @Test
    void test2(){
        //修改数据
        Users users = new Users();
        users.setName("测试修改");
        users.setUserId(19);
        users.setPassword("123456");
        users.setGroupId(2);
        System.out.println(usersService.updateById(users));
    }
    @Test
    void test3(){
        //删除多条数据
        List<Integer> list = new ArrayList<>();
        list.add(16);
        list.add(17);
        list.add(18);
        System.out.println(usersService.removeByIds(list));
    }
}
