package pers.zzh.competition.controller;

import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Groups;
import pers.zzh.competition.service.GroupsService;
import pers.zzh.competition.utils.Result;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GroupsController {

    @Resource
    private GroupsService groupsService;

    // 判断组编码输入的是否存在,并返回id
    @GetMapping("/encoding/{encoding}")
    public Result verifyEncodingPhoneEmail(@PathVariable String encoding) {
        List<Groups> list =  groupsService.selectEncoding(encoding);
        return list.isEmpty() ?  new Result(100,"查询结果为空", Collections.emptyMap()) :  new Result(100,"查询存在此编码",list);
    }

    // 添加组并反回组id
    @PostMapping("/addGroup")
    public Result addGroup(@RequestBody Groups groups) {
        int num =  groupsService.insertGroupGetId(groups);
        return num == 0 ?  new Result(201,"创建失败，组名或是编码已存在。",Collections.emptyMap()) :  new Result(200,"创建成功",num);
    }

    // 查询单个组
    @GetMapping("/group/{id}")
    public Result getGroupName(@PathVariable String id) {
        Groups groups = groupsService.getGroupName(id);
        String name;
        try {
            name = groups.getGroupName();
        }catch (Exception e){
            name = "";
        }
        return name.equals("") ?  new Result(201,"获取用户组失败",Collections.emptyMap()) :  new Result(200,"成功获取用户组",name);
    }
}
