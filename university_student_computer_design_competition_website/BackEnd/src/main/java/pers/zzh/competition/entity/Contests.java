package pers.zzh.competition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class Contests {
    @TableId
    private Integer contestId;
    private String contestTitle;
    private String contestText;
    private Integer promulgator;
    private Integer groupId;
    private String regStartTime;
    private String regEndTime;
    private String startTime;
    private String endTime;

    @TableField(exist = false)
    private String name;

    @TableField(exist = false)
    private String groupName;
}
