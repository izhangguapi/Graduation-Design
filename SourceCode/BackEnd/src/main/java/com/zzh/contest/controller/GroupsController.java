package com.zzh.contest.controller;

import com.zzh.contest.entity.Groups;
import com.zzh.contest.entity.dto.PageQuery;
import com.zzh.contest.service.GroupsService;
import com.zzh.contest.utils.result.Result;
import com.zzh.contest.utils.result.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端控制器 - groups表的操作
 */
@RestController
@RequiredArgsConstructor
public class GroupsController {
    final GroupsService service;

    @GetMapping("/encoding/{encoding}")
    public Result verifyEncodingPhoneEmail(@PathVariable String encoding) {
        Groups groups = service.selectEncoding(encoding);
        return groups == null
                ? Result.failure(ResultCode.SELECT_IS_EMPTY)
                : Result.success(ResultCode.SELECT_SUCCESS, groups);
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