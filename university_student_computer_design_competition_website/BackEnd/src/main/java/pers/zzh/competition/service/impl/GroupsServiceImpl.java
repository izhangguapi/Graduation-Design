package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.zzh.competition.dao.GroupsDao;
import pers.zzh.competition.entity.Groups;
import pers.zzh.competition.service.GroupsService;

@Service
public class GroupsServiceImpl extends ServiceImpl<GroupsDao, Groups> implements GroupsService {
}
