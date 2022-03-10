package pers.zzh.competition.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import pers.zzh.competition.entity.Contests;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.mapper.ContestsMapper;
import pers.zzh.competition.mapper.UsersMapper;
import pers.zzh.competition.service.ContestsService;
import pers.zzh.competition.service.GroupsService;
import pers.zzh.competition.service.UsersService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 平均分配参赛者数量的算法
 *
 * @author zhangguapi
 */

public class AverageAlgorithm {

    private static List<Users> usersList;
    private static List<Contests> contestsList;

    @Resource
    private static UsersMapper usersMapper;
    @Resource
    private static ContestsMapper contestsMapper;

    /**
     * 算法
     *
     * @param gid 组id
     */
    public static void entrance(Integer gid) {
        selectUsers(gid);
        selectContests(gid);
        loopForCompute(contestsList);
    }

    /**
     * 循环比赛列表
     */
    private static void loopForCompute(List<Contests> list) {
        for (Contests contests : list) {
            // 参赛者数量
            int participantsNumber = contests.getNumber();
            // 判断参赛者数量是否为0
            if (participantsNumber != 0) {
                compute(participantsNumber, usersList.size());
            }
        }
    }

    /**
     * 用参赛者除以评审人数量，判断是否可以整除，如果可以
     *
     * @param dividend 被除数
     * @param divisor  除数
     */
    private static void compute(int dividend, int divisor) {
        // 余数
        int remainder = dividend % divisor;
        // 商
        int quotient = dividend / divisor;
        // 如果余数等于0，就传商值，否则两个都传
        if (remainder == 0) {
            quotient(quotient);
        } else {
            quotientAndRemainder(quotient, remainder);
        }
    }

    /**
     * 余数不为0的情况
     *
     * @param quotient  商
     * @param remainder 余数
     */
    private static void quotientAndRemainder(int quotient, int remainder) {
        // 创建Map对象 //数据采用的哈希表结构
        Map<String, Integer> map = new HashMap<>(usersList.size() * 2);
        int i = 0;
        for (Users users : usersList) {
            if (i < remainder) {
                map.put("userN" + users.getUserId(), i * quotient + i);
                map.put("userM" + users.getUserId(), quotient + 1);
            } else if (i == remainder) {
                map.put("userN" + users.getUserId(), i * quotient + remainder);
                map.put("userM" + users.getUserId(), quotient);
            } else {
                map.put("userN" + users.getUserId(), i * quotient + remainder);
                map.put("userM" + users.getUserId(), quotient);
            }
            i++;
        }
        System.out.println(map);
    }

    /**
     * 仅有商的情况下调用
     *
     * @param quotient 商
     */
    private static void quotient(int quotient) {

        // 创建Map对象 //数据采用的哈希表结构
        Map<String, Integer> map = new HashMap<>(usersList.size() * 2);
        int i = 0;
        for (Users users : usersList) {
            map.put("userN" + users.getUserId(), i * quotient);
            map.put("userM" + users.getUserId(), quotient);
            i++;
        }
        System.out.println(map);
    }

    private static void selectUsers(Integer gid) {
        // 根据组id查询该组下有哪用户，生成一个用户id列表
        QueryWrapper<Users> qwUsers = new QueryWrapper<>();
        qwUsers.select("user_id").eq("group_id", gid);
        usersList = usersMapper.selectList(qwUsers);
    }

    private static void selectContests(Integer gid) {
        // 根据组id查询有哪些比赛以及报名人数，生成一个组id和
        QueryWrapper<Contests> qwContests = new QueryWrapper<>();
        qwContests.select("contests.contest_id,COUNT(scores_id) number")
                .last("LEFT JOIN scores ON contests.contest_id=scores.contest_id \n" +
                        "WHERE contests.group_id = " + gid + " GROUP BY contest_id");
        contestsList = contestsMapper.selectList(qwContests);
    }
}
