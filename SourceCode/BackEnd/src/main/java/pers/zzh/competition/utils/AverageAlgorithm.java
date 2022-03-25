package pers.zzh.competition.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.zzh.competition.entity.Contests;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.service.ContestsService;
import pers.zzh.competition.service.UsersService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 平均分配参赛者数量的算法
 *
 * @author zhangguapi
 */
@Component
public class AverageAlgorithm {


    private static UsersService usersService;
    private static ContestsService contestsService;

    private static List<Users> usersList;

    private static String path;

    /**
     * 算法
     *
     * @param gid 组id
     */

    public static void entrance(Integer gid) {
        path = "/group" + gid;
        // 获取用户列表
        usersList = usersService.selectByGid(gid);
        // 获取比赛列表
        List<Contests> contestsList = contestsService.selectByGid(gid);
        // 循环比赛列表，取出比赛id和比赛人数
        loopForCompute(contestsList);
    }


    /**
     * 循环比赛列表
     */
    private static void loopForCompute(List<Contests> list) {
        for (Contests contests : list) {

            String afterPath = path + "/Contest" + contests.getContestId() + ".json";
            // 参赛者数量
            int participantsNumber = contests.getNumber();
            // 判断参赛者数量是否为0
            if (participantsNumber != 0) {
                Map<String, Integer> map = compute(participantsNumber, usersList.size());
                if (map.get("remainder") == null) {
                    createMap(map.get("quotient"), afterPath);
                } else {
                    createMap(map.get("quotient"), map.get("remainder"), afterPath);
                }
            }
        }
    }

    /**
     * 用参赛者除以评审人数量，判断是否可以整除，如果可以
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return map
     */
    private static Map<String, Integer> compute(int dividend, int divisor) {
        // 余数
        int remainder = dividend % divisor;
        // 商
        int quotient = dividend / divisor;
        // 如果余数等于0，就传商值，否则两个都传
        Map<String, Integer> map = new HashMap<>(2);
        map.put("quotient", quotient);
        if (remainder != 0) {
            map.put("remainder", remainder);
        }
        return map;
    }

    /**
     * 生成map，仅有商的情况下调用
     *
     * @param quotient 商
     */
    private static void createMap(int quotient, String afterPath) {
        // 创建Map对象 //数据采用的哈希表结构
        Map<String, Integer> map = new HashMap<>(usersList.size() * 2);
        int i = 0;
        for (Users users : usersList) {
            map.put("userL" + users.getUserId(), i * quotient);
            map.put("userR" + users.getUserId(), quotient);
            i++;
        }
        // 保存文件
        SaveFile.saveJson(map, afterPath);
    }

    /**
     * 生成map，余数不为0的情况
     *
     * @param quotient  商
     * @param remainder 余数
     */
    private static void createMap(int quotient, int remainder, String afterPath) {
        // 创建Map对象 //数据采用的哈希表结构
        Map<String, Integer> map = new HashMap<>(usersList.size() * 2);
        int i = 0;
        for (Users users : usersList) {
            if (i < remainder) {
                map.put("userL" + users.getUserId(), i * quotient + i);
                map.put("userR" + users.getUserId(), quotient + 1);
            } else if (i == remainder) {
                map.put("userL" + users.getUserId(), i * quotient + remainder);
                map.put("userR" + users.getUserId(), quotient);
            } else {
                map.put("userL" + users.getUserId(), i * quotient + remainder);
                map.put("userR" + users.getUserId(), quotient);
            }
            i++;
        }
        // 保存文件
        SaveFile.saveJson(map, afterPath);

    }

    @Autowired
    public void setUsersService(UsersService usersService) {
        AverageAlgorithm.usersService = usersService;
    }

    @Autowired
    public void setContestsService(ContestsService contestsService) {
        AverageAlgorithm.contestsService = contestsService;
    }
}
