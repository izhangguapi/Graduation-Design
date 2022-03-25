package pers.zzh.competition.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.zzh.competition.entity.Groups;
import pers.zzh.competition.service.GroupsService;
import pers.zzh.competition.vo.Result;
import pers.zzh.competition.vo.ResultCode;

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
        return list.isEmpty()
                ? Result.fail(ResultCode.SELECT_IS_EMPTY)
                : Result.success(ResultCode.SELECT_SUCCESS, list);
    }

    @PostMapping("/insertGroup")
    public Result addGroup(@RequestBody Groups groups) {
        int gid = service.insertGroupGetId(groups);
        return gid == 0
                ?  Result.fail(ResultCode.ADD_FAIL)
                :  Result.success(ResultCode.ADD_SUCCESS, gid);
    }

    @GetMapping("/selectGroupsList")
    public Result getGroupsList() {
        return Result.success(ResultCode.SELECT_SUCCESS,service.selectGroupsList());
    }

    @PostMapping("/deleteGroups")
    public Result deleteGroups(@RequestBody List<Groups> groups) {
        try {
            service.removeBatchByIds(groups);
        } catch (Exception e) {
            return  Result.fail(ResultCode.DELETE_FAIL);
        }
        return  Result.success(ResultCode.DELETE_SUCCESS);
    }

    @GetMapping("/searchGroups")
    public Result searchGroups(@RequestParam String query) {
        return Result.success(ResultCode.SELECT_SUCCESS,service.searchGroups(query));
    }

    @PutMapping("/updateGroup")
    public Result updateGroup(@RequestBody Groups groups) {
        return Result.success(ResultCode.SELECT_SUCCESS,service.updateById(groups));
    }
}