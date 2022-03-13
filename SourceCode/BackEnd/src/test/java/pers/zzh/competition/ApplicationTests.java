package pers.zzh.competition;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.zzh.competition.common.AverageAlgorithm;
import pers.zzh.competition.entity.Groups;
import pers.zzh.competition.entity.Scores;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.mapper.*;
import pers.zzh.competition.service.UsersService;

import java.io.File;
import java.util.HashMap;
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
    @Autowired
    private UsersService usersService;

    @Test
    void testGetBy0() {
        LambdaQueryWrapper<Users> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Users::getPhone, "13886961359");
        lqw.eq(Users::getPassword, "13886961359");
        System.out.println(usersDao.selectList(lqw));
    }

    @Test
    void testGetPhone() {
//        Contests contests = new Contests();
//        contests.setContestTitle("测试sad");
//        contests.setContestText("## 请输入比赛内容\nasas\nasfg\nas\ng\naasg");
//        contests.setPromulgator(3);
//        contests.setGroupId(4);
//        contests.setStartTime("2022-01-21 08:00:00");
//        contests.setEndTime("2022-01-21 08:00:00");
//        contestsMapper.insert(contests);
        //System.out.println("我直接好家伙\\n测试\\\n");
//        String test="1,测试啊啊。\n "+ "2,对对对 ";
//        test.replace
//        System.out.println("=================>>>>>>>>>>>>>>>"+test);
        //hrEffPfmcePlaneePo.setWorkContent(test); //存入数据库。
        // hrEffPfmcePlaneeDao.create(hrEffPfmcePlaneePo);

        int cid = 7;
        int uid = 7;
        QueryWrapper<Scores> qw = new QueryWrapper<>();
        qw.eq("contest_id", cid).eq("contestant", uid);
        if (scoresDao.selectCount(qw) == 0) {

        }
    }

    @Test
    void testGetBy() {
        //LambdaQueryWrapper条件查询
        String name = "";
        LambdaQueryWrapper<Users> lqw = new LambdaQueryWrapper<>();
        lqw.like(!Strings.isNullOrEmpty(name), Users::getName, name);
        System.out.println(usersDao.selectList(lqw));
    }

    @Test
    void testGetBy2() {
        //QueryWrapper条件查询
        String name = "江";
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.like(!Strings.isNullOrEmpty(name), "name", name);
        System.out.println(usersDao.selectList(qw));
    }

    @Test
    void testGetPage() {
        //分页查询users
        IPage<Users> page = new Page<>(3, 5);
        usersDao.selectPage(page, null);
        System.out.println("数据：" + page.getRecords());
        System.out.println("总共可以分为" + page.getPages() + "页");
        System.out.println("当前是第" + page.getCurrent() + "页");
        System.out.println("每页" + page.getSize() + "条数据");
        System.out.println("数据一共" + page.getTotal() + "条");
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

        Page<Users> page = new Page<>(0, 5);
        QueryWrapper<Users> qw = new QueryWrapper<>();
        qw.select("*");
        qw.last("inner join groups ON users.group_id = groups.group_id order by user_id;");
        // SELECT * FROM users inner join groups ON users.group_id = groups.group_id order by user_id
        //返回结果
        System.out.println("数据：" + usersDao.selectPage(page, qw).toString());
    }

    @Test
    void testInsert() {
        //添加一条数据
        Groups groups = new Groups();
        groups.setEncoding("12544");
        groups.setGroupName("4245d");
//        QueryWrapper<Users> qw = new QueryWrapper<>();
//        qw.last(";SELECT LAST_INSERT_ID();");
        System.out.println(groupsDao.insert(groups));
        //System.out.println("插入后："+groups.getGroupId());
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

//    @Test
//    void algorithm1() {
//        // 获取组id
//        int gid = 3;
//        // 根据组id查询该组下有哪用户，生成一个用户id列表
//        QueryWrapper<Users> qwUsers = new QueryWrapper<>();
//        qwUsers.select("user_id").eq("group_id", gid);
//        List<Users> usersList = usersDao.selectList(qwUsers);
//        // 评审人数量
//        int usersNumber = usersList.size();
//        System.out.println("评审人数量" + usersNumber);
//        // 根据组id查询有哪些比赛以及报名人数，生成一个组id和
//        QueryWrapper<Contests> qwContests = new QueryWrapper<>();
//        qwContests.select("contests.contest_id,COUNT(scores_id) number")
//                .last("LEFT JOIN scores ON contests.contest_id=scores.contest_id \n" +
//                        "WHERE contests.group_id = " + gid + " GROUP BY contest_id");
//        List<Contests> contestsList = contestsMapper.selectList(qwContests);
//        // 分配审核人
//        for (Contests contests : contestsList) {
//            // 比赛id
//            int cid = contests.getContestId();
//            System.out.println("比赛id" + cid);
//            // 参赛者数量
//            int participantsNumber = contests.getNumber();
//            System.out.println("参赛者数量" + participantsNumber);
//            // 判断参赛者数量是否为0
//            if (participantsNumber != 0) {
//                // 用参赛者除以评审人数量
//                algorithm1(participantsNumber, usersNumber);
//            }
//        }
//    }
//
//    // 用参赛者除以评审人数量，判断是否可以整除，如果可以
//    void algorithm1(int dividend, int divisor) {
//        // 余数
//        int remainder = dividend % divisor;
//        // 商
//        int quotient = dividend / divisor;
//        // 如果余数等于0，就传除数
//        if (remainder == 0) {
//            algorithm2(quotient);
//        } else {
//            // 否则两个都传
//            algorithm2(quotient, remainder);
//        }
//    }
//
//    // 余数不为0的情况
//    void algorithm2(int a, int b) {
//        AverageAlgorithm.entrance(3);
//    }

    @Test
    void testDelete123() {
        QueryWrapper<Scores> qw = new QueryWrapper<>(); // 创建条件构造器
        qw.select("*").last("inner join `contests` ON scores.contest_id = contests.contest_id WHERE contestant = 7");
        System.out.println(scoresDao.selectList(qw));
    }


//    @Autowired
//    private AverageAlgorithm averageAlgorithm;

    @Test
    void testAlgorithm() {
        AverageAlgorithm.entrance(3);
    }

    @Test
    void ar() {
        int a = 17;
        int b = 2;
        // 创建Map对象 //数据采用的哈希表结构
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            System.out.print(i + "," + b + ",");
            System.out.println(i == b);
            // 第一次
            if (i < b) {
                map.put("userStart" + i, i * a + i);
                map.put("userEnd" + i, a + 1);
            } else if (i == b) {
                map.put("userStart" + i, i * a + b);
                map.put("userEnd" + i, a);
            } else {
                map.put("userStart" + i, i * a + b);
                map.put("userEnd" + i, a);
            }
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(map.get("userStart" + i) + "," + map.get("userEnd" + i));
        }

    }

    @Test
    void algorithm2() {
        // 获取当前用户目录
        String path = System.getProperty("user.home") + "/.ReviewAllot";
        System.out.println(path);
        // 以当前用户目录路径实例化一个File对象
        File file = new File(path);
        // 如果不存在，则创建目录
        if (!file.exists()) {
            System.out.println(file.mkdirs());
        }
//        try {
//            // 保证创建一个新文件
//            File file = new File(fullPath);
//            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
//                file.getParentFile().mkdirs();
//            }
//            if (file.exists()) { // 如果已存在,删除旧文件
//                file.delete();
//            }
//            file.createNewFile();
//
//            // 格式化json字符串
//            jsonString = JsonFormatTool.formatJson(jsonString);
//
//            // 将格式化后的字符串写入文件
//            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
//            write.write(jsonString);
//            write.flush();
//            write.close();
//        } catch (Exception e) {
//            System.out.println(false);
//            e.printStackTrace();
//        }
    }
}
