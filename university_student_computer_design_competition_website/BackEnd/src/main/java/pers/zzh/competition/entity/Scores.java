package pers.zzh.competition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Scores {
    @TableId
    private Integer scoresId;
    private Integer contestId;
    private Integer contestant;
    private Boolean state;
    private Integer judge;
    private String text;
    private Integer result;

    @TableField(exist = false)
    private Users Users;

    @TableField(exist = false)
    private Contests contests;

    @Override
    public String toString() {
        return "Scores{" +
                "scores_id=" + scoresId +
                ", contest_id=" + contestId +
                ", contestant=" + contestant +
                ", state=" + state +
                ", judge=" + judge +
                ", text='" + text + '\'' +
                ", result=" + result +
                ", Users=" + Users +
                ", contests=" + contests +
                '}' + '\n';
    }
}
