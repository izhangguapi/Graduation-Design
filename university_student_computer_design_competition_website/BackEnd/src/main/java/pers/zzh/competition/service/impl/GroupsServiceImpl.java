package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.zzh.competition.entity.Users;
import pers.zzh.competition.mapper.GroupsMapper;
import pers.zzh.competition.entity.Groups;
import pers.zzh.competition.service.GroupsService;

import java.util.List;
import java.util.Map;

@Service
public class GroupsServiceImpl extends ServiceImpl<GroupsMapper, Groups> implements GroupsService {

    // 查询组
    @Override
    public List<Groups> selectEncoding(String encoding) {
        QueryWrapper<Groups> qw = new QueryWrapper<>();
        qw.eq("encoding",encoding);
        qw.last(" AND group_id NOT IN ('1','2','3')");
        return baseMapper.selectList(qw);
    }
}
