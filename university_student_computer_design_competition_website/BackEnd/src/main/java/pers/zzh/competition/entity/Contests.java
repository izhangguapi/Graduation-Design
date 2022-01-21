package pers.zzh.competition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Contests {
    @TableId
    private Integer contestId;
    private String contestTitle;
    private String contestText;
    private Integer promulgator;
    private Integer groupId;
    private String startTime;
    private String endTime;

    @TableField(exist = false)
    private String name;

    @TableField(exist = false)
    private String groupName;

    @Override
    public String toString() {
        return "Contests{" +
                "contest_id=" + contestId +
                ", contest_title='" + contestTitle + '\'' +
                ", contest_text='" + contestText + '\'' +
                ", promulgator=" + promulgator +
                ", group_id=" + groupId +
                ", start_time='" + startTime + '\'' +
                ", end_time='" + endTime + '\'' +
                '}' + '\n';
    }
}
