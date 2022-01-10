package pers.zzh.competition;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.zzh.competition.dao.*;
import pers.zzh.competition.entities.*;

@SpringBootTest
class ApplicationTests {


    @Autowired
    private UsersDao usersDao;
    @Autowired
    private GroupsDao groupsDao;
    @Autowired
    private ScoresDao scoresDao;
    @Autowired
    private MessagesDao messagesDao;
    @Autowired
    private ContestsDao contestsDao;

    @Test
    void testGetAll() {
        //查询全部users
        System.out.println(scoresDao.selectList(null));
    }
    @Test
    void contextLoads() {
        //查询全部（链表查询）
        System.out.println(usersDao.selectAllUser());
    }
    @Test
    void testInsert() {
        //添加一条数据
        Users users = new Users();
        users.setPassword("123456");
        users.setGroupId(3);
        users.setName("test");
        System.out.println(usersDao.insert(users));
    }
    @Test
    void testUpdata() {
        //修改一条数据
        Users users = new Users();
        users.setUserId(15);
        users.setPassword("456123");
        users.setGroupId(2);
        users.setName("test更新");
        usersDao.updateById(users);
    }
    @Test
    void testDelete() {
        //删除一条数据
        System.out.println(usersDao.deleteById(15));
    }

}
