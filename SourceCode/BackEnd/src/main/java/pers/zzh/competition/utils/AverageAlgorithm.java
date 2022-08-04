package pers.zzh.competition.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.zzh.competition.entity.Contests;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.service.ContestsService;
import pers.zzh.competition.service.UsersService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    private static String UID;

    private static final Map<String, Integer> limitMap = new HashMap<>(2);

    /**
     * 获取Limit
     *
     * @param uid 用户id
     * @param gid 组id
     * @param cid 比赛id
     */
    public static Map<String, Integer> getLimit(String uid, String gid, int cid) {
        UID = uid;
        // 组合路径
        String string = "/group" + gid + "/Contest" + cid + ".json";
        // 从路径json文件获取值
        JSONObject jsonObject = JSONUtil.parseObj(FileOperations.readJsonFile(string));
        System.out.println(jsonObject);
        String limitL = jsonObject.getStr("limitL" + uid);
        String limitR = jsonObject.getStr("limitR" + uid);
        System.out.println("左：" + limitL + "\n右：" + limitR);
        Map<String, Integer> map = new HashMap<>(2);
        // 获取的值为null就执行算法，否则直接返回获取到的值
        if (limitL == null || limitR == null) {
            System.out.println("读取失败，创建新的json文件");
            initialize(gid, cid);
            return limitMap;
        } else {
            System.out.println("读取成功");
            map.put("limitL", Integer.valueOf(Objects.requireNonNull(jsonObject).getStr("limitL" + uid)));
            map.put("limitR", Integer.valueOf(Objects.requireNonNull(jsonObject).getStr("limitR" + uid)));
        }
        return map;
    }

    /**
     * 算法初始化
     *
     * @param gid 组id
     */
    public static void initialize(String gid, int cid) {
        path = "/group" + gid;
        // 获取用户列表
        usersList = usersService.selectByGid(Integer.valueOf(gid));
        // 获取比赛列表
        loopForCompute(contestsService.selectByCid(cid), cid);
    }

    /**
     * 循环比赛列表
     */
    private static void loopForCompute(Contests contests, int cid) {
        String afterPath = path + "/Contest" + cid + ".json";
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
            map.put("limitL" + users.getUserId(), i * quotient);
            map.put("limitR" + users.getUserId(), quotient);
            i++;
        }
        // 保存文件
        FileOperations.saveJsonFile(map, afterPath);

        setMap(map);
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
                map.put("limitL" + users.getUserId(), i * quotient + i);
                map.put("limitR" + users.getUserId(), quotient + 1);
            } else if (i == remainder) {
                map.put("limitL" + users.getUserId(), i * quotient + i);
                map.put("limitR" + users.getUserId(), quotient);
            } else {
                map.put("limitL" + users.getUserId(), i * quotient + remainder);
                map.put("limitR" + users.getUserId(), quotient);
            }
            i++;
        }
        System.out.println(map);
        // 保存文件
        FileOperations.saveJsonFile(map, afterPath);

        setMap(map);
    }

    private static void setMap(Map<String, Integer> map) {
        limitMap.put("limitL", map.get("limitL" + UID));
        limitMap.put("limitR", map.get("limitR" + UID));
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
