package pers.zzh.competition.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Groups;
import pers.zzh.competition.service.GroupsService;
import pers.zzh.competition.vo.Result;
import pers.zzh.competition.vo.ResultCode;
import pers.zzh.competition.vo.params.PageQuery;

import java.util.List;

/**
 * 前端控制器 - groups表的操作
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GroupsController {
    final GroupsService service;

    @GetMapping("/encoding/{encoding}")
    public Result verifyEncodingPhoneEmail(@PathVariable String encoding) {
        List<Groups> list = service.selectEncoding(encoding);
        return list.isEmpty()
                ? Result.failure(ResultCode.SELECT_IS_EMPTY)
                : Result.success(ResultCode.SELECT_SUCCESS, list);
    }

    @PostMapping("/insertGroup")
    public Result addGroup(@RequestBody Groups groups) {
        Integer gid = service.insertGroupGetId(groups);
        return gid == 0
                ? Result.failure(ResultCode.ADD_FAIL)
                : Result.success(ResultCode.ADD_SUCCESS, gid);
    }

    @PostMapping("/groups/list")
    public Result selectGroupsList(@RequestBody PageQuery pageQuery) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.selectGroupsList(pageQuery));
    }

    @PostMapping("/deleteGroups")
    public Result deleteGroups(@RequestBody List<Groups> groups) {
        try {
            service.removeBatchByIds(groups);
        } catch (Exception e) {
            return Result.failure(ResultCode.DELETE_FAIL);
        }
        return Result.success(ResultCode.DELETE_SUCCESS);
    }

    @PutMapping("/updateGroup")
    public Result updateGroup(@RequestBody Groups groups) {
        return Result.success(ResultCode.SELECT_SUCCESS, service.updateById(groups));
    }
}