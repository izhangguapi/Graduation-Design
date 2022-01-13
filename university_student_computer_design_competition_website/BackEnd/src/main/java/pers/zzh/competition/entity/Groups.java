package pers.zzh.competition.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Groups {
    @TableId
    private Integer groupId;
    private String groupName;
    private String encoding;

    @Override
    public String toString() {
        return "Groups{" +
                "groupId=" + groupId +
                ", group_name='" + groupName + '\'' +
                ", encoding=" + encoding +
                '}' + '\n';
    }
}
