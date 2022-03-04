package pers.zzh.competition.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Groups;
import pers.zzh.competition.service.GroupsService;
import pers.zzh.competition.utils.Result;


import java.util.Collections;
import java.util.List;

/**
 * @author 张恣豪
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GroupsController {

    final GroupsService service;

    /**
     * 判断组编码输入的是否存在,并返回id
     * @param encoding
     * @return
     */
    @GetMapping("/encoding/{encoding}")
    public Result verifyEncodingPhoneEmail(@PathVariable String encoding) {
        List<Groups> list =  service.selectEncoding(encoding);
        return list.isEmpty() ?  new Result(100,"查询结果为空", Collections.emptyMap()) :  new Result(100,"查询存在此编码",list);
    }

    /**
     * 添加组并反回组id
     * @param groups
     * @return
     */
    @PostMapping("/addGroup")
    public Result addGroup(@RequestBody Groups groups) {
        int num =  service.insertGroupGetId(groups);
        return num == 0 ?  new Result(201,"创建失败，组名或是编码已存在。",Collections.emptyMap()) :  new Result(200,"创建成功",num);
    }

    /**
     * 查询单个组
     * @param id
     * @return
     */
    @GetMapping("/group/{id}")
    public Result getGroupName(@PathVariable String id) {
        Groups groups = service.getGroupName(id);
        String name;
        try {
            name = groups.getGroupName();
        }catch (Exception e){
            name = "";
        }
        return "".equals(name) ?  new Result(201,"获取用户组失败",Collections.emptyMap()) :  new Result(200,"成功获取用户组",name);
    }
}