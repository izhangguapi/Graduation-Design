package pers.zzh.competition.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.zzh.competition.entity.Contests;
import pers.zzh.competition.mapper.ContestsMapper;
import pers.zzh.competition.service.ContestsService;

@Service
public class ContestsServiceImpl extends ServiceImpl<ContestsMapper, Contests> implements ContestsService {

    @Override
    public int insertContests(Contests contests) {
        int num;
        try {
            baseMapper.insert(contests);
            num =  contests.getGroupId();
        }catch (Exception e){
            num = 0;
        }
        return num;

    }
}
