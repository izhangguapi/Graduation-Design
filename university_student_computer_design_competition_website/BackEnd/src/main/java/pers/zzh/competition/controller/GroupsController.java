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

    @GetMapping("/encoding/{encoding}")
    public Result verifyEncodingPhoneEmail(@PathVariable String encoding) {
        List<Groups> list = service.selectEncoding(encoding);
        return list.isEmpty() ? new Result(100, "查询结果为空", Collections.emptyMap()) : new Result(100, "查询存在此编码", list);
    }

    @PostMapping("/insertGroup")
    public Result addGroup(@RequestBody Groups groups) {
        int gid = service.insertGroupGetId(groups);
        return gid == 0
                ? new Result(201, "创建失败，组名称或组编码已存在。", false)
                : new Result(200, "创建成功", gid);
    }

    @GetMapping("/selectGroupsList")
    public Result getGroupsList() {
        return new Result(service.selectGroupsList());
    }

    @PostMapping("/deleteGroups")
    public Result deleteGroups(@RequestBody List<Groups> groups) {
        try {
            service.removeBatchByIds(groups);
        } catch (Exception e) {
            return new Result(false);
        }
        return new Result(true);
    }

    @GetMapping("/searchGroups")
    public Result searchGroups(@RequestParam String query) {
        return new Result(service.searchGroups(query));
    }

    @PutMapping("/updateGroup")
    public Result updateGroup(@RequestBody Groups groups) {
        return new Result(service.updateById(groups));
    }
}