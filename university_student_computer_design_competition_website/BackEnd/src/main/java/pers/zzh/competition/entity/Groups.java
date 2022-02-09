package pers.zzh.competition.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Groups {
    @TableId
    private Integer groupId;
    private String groupName;
    private String encoding;
}
