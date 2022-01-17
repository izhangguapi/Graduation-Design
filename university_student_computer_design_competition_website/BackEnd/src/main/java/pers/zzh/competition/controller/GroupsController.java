package pers.zzh.competition.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.zzh.competition.entity.Groups;
import pers.zzh.competition.mapper.GroupsMapper;
import pers.zzh.competition.service.GroupsService;
import pers.zzh.competition.utils.Result;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GroupsController {

    @Autowired
    private GroupsService groupsService;

    // 判断组编码输入的是否存在
    @GetMapping("/encoding/{encoding}")
    public Result verifyEncodingPhoneEmail(@PathVariable String encoding) {
        List<Groups> list =  groupsService.selectEncoding(encoding);
        if (list.isEmpty()){
            return new Result(200,"查询结果为空",null);
        }else {
            return new Result(200,"查询存在数据",list);
        }
    }
}
