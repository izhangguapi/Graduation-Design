package pers.zzh.competition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.zzh.competition.mapper.*;
import pers.zzh.competition.entity.*;
import pers.zzh.competition.service.GroupsService;

import java.util.Map;

@SpringBootTest
class ApplicationTests {


    @Autowired
    private UsersMapper usersDao;
    @Autowired
    private GroupsMapper groupsDao;
    @Autowired
    private ScoresMapper scoresDao;
    @Autowired
    private MessagesMapper messagesDao;
    @Autowired
    private ContestsMapper contestsMapper;
    @Test
    void testGetBy0() {
        LambdaQueryWrapper<Users> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Users::getPhone,"13886961359");
        lqw.eq(Users::getPassword,"13886961359");
        System.out.println(usersDao.selectList(lqw));
    }
    @Test
    void testGetPhone() {

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
//        Page<Map<String,Object>> page =new Page<Map<String,Object>>(4,4);
//        System.out.println(usersDao.selectAllUsersAndGroups(page));
//        System.out.println("总共可以分为"+page.getPages()+"页");
//        System.out.println("当前是第"+page.getCurrent()+"页");
//        System.out.println("每页"+page.getSize()+"条数据");
//        System.out.println("数据一共"+page.getTotal()+"条");

        Page<Users> page = new Page<>(0,5);
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.select("*");
        qw.last("inner join groups ON users.group_id = groups.group_id order by user_id;");
        // SELECT * FROM users inner join groups ON users.group_id = groups.group_id order by user_id
        //返回结果
        System.out.println("数据："+usersDao.selectPage(page,qw).toString());
    }

    private GroupsService groupsService;
    @Test
    void testInsert() {
        //添加一条数据
        Groups groups = new Groups();
        groups.setEncoding("12544");
        groups.setGroupName("4245d");
//        QueryWrapper<Users> qw = new QueryWrapper<>();
//        qw.last(";SELECT LAST_INSERT_ID();");
        System.out.println(groupsDao.insertGroupGetId(groups));
        System.out.println("插入后："+groups.getGroupId());
//        System.out.println(groupsDao.insert());
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
