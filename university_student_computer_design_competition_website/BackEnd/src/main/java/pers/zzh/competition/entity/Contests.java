package pers.zzh.competition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Contests {
    @TableId
    private Integer contestId;
    private String contest_title;
    private String contest_text;
    private Integer publisher;
    private Integer groupId;
    private String startTime;
    private String endTime;

    @TableField(exist = false)
    private Users Users;

    @TableField(exist = false)
    private Groups groups;

    @Override
    public String toString() {
        return "Contests{" +
                "contest_id=" + contestId +
                ", contest_title='" + contest_title + '\'' +
                ", contest_text='" + contest_text + '\'' +
                ", promulgator=" + publisher +
                ", group_id=" + groupId +
                ", start_time='" + startTime + '\'' +
                ", end_time='" + endTime + '\'' +
                ", Users=" + Users +
                ", groups=" + groups +
                '}' + '\n';
    }
}
