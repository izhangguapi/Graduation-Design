package pers.zzh.competition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.zzh.competition.dao.*;
import pers.zzh.competition.entity.*;

import java.util.Map;

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
    void testGetBy0() {
        LambdaQueryWrapper<Users> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Users::getPhone,"13886961359");
        lqw.eq(Users::getPassword,"13886961359");
        System.out.println(usersDao.selectList(lqw));
    }



    @Test
    void testGetBy() {
        //LambdaQueryWrapper条件查询
        String name = "";
        LambdaQueryWrapper<Users> lqw = new LambdaQueryWrapper<>();
        lqw.like(!Strings.isNullOrEmpty(name),Users::getName,name);
        System.out.println(usersDao.selectList(lqw));
    }
    @Test
    void testGetBy2() {
        //QueryWrapper条件查询
        String name = "江";
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.like(!Strings.isNullOrEmpty(name),"name",name);
        System.out.println(usersDao.selectList(qw));
    }
    @Test
    void testGetPage() {
        //分页查询users
        IPage<Users> page = new Page<>(0,5);
        usersDao.selectPage(page,null);
        System.out.println("数据："+page.getRecords());
        System.out.println("总共可以分为"+page.getPages()+"页");
        System.out.println("当前是第"+page.getCurrent()+"页");
        System.out.println("每页"+page.getSize()+"条数据");
        System.out.println("数据一共"+page.getTotal()+"条");
    }
    @Test
    void testGetAll() {
        //查询全部users
        System.out.println(usersDao.selectList(null));
    }
    @Test
    void contextLoads() {
        //分页链表查询users和group，配合dao层的接口，主要用于管理员操作
        Page<Map<String,Object>> page =new Page<Map<String,Object>>(4,4);
        System.out.println(usersDao.selectAllUsersAndGroups(page));
        System.out.println("总共可以分为"+page.getPages()+"页");
        System.out.println("当前是第"+page.getCurrent()+"页");
        System.out.println("每页"+page.getSize()+"条数据");
        System.out.println("数据一共"+page.getTotal()+"条");
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
    void testUpData() {
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
