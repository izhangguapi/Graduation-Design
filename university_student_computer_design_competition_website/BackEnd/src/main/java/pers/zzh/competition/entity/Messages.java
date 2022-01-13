package pers.zzh.competition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Messages {
    @TableId
    private Integer messageId;
    private Integer recipient;
    private String text;
    private Integer sender;
    private String time;
    private Boolean state;

    @TableField(exist = false)
    private Users Users;

    @Override
    public String toString() {
        return "Messages{" +
                "message_id=" + messageId +
                ", recipient=" + recipient +
                ", text='" + text + '\'' +
                ", sender=" + sender +
                ", time='" + time + '\'' +
                ", state=" + state +
                ", Users=" + Users +
                '}' + '\n';
    }
}
