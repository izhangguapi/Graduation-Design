package pers.zzh.competition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contests {
    @TableId
    private Integer contestId;
    private String contestTitle;
    private String contestText;
    private String url;
    private Integer promulgator;
    private Integer groupId;
    private String regStartTime;
    private String regEndTime;
    private String startTime;
    private String endTime;

    private String name;
    private String groupName;
}
