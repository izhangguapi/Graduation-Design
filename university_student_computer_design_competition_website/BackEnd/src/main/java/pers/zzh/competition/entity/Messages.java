package pers.zzh.competition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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
}
