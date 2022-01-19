package pers.zzh.competition.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Groups;
import pers.zzh.competition.service.GroupsService;
import pers.zzh.competition.utils.Result;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GroupsController {

    @Autowired
    private GroupsService groupsService;

    // 判断组编码输入的是否存在
    @GetMapping("/encoding/{encoding}")
    public Result verifyEncodingPhoneEmail(@PathVariable String encoding) {
        List<Groups> list =  groupsService.selectEncoding(encoding);
        return list.isEmpty() ?  new Result(100,"查询结果为空",null) :  new Result(100,"查询存在此编码",list);
    }

    // 添加组并反回组id
    @GetMapping("/addGroup")
    public Result addGroup(@RequestBody Groups groups) {
        int num =  groupsService.insertGroup(groups);
        return num>0 ?  new Result(200,"添加成功",null) :  new Result(100,"添加失败",null);
    }
}
