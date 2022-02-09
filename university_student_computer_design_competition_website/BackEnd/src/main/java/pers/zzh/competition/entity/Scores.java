package pers.zzh.competition.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Scores {
    @TableId
    private Integer scoresId;
    private Integer contestId;
    private Integer contestant;
    private Boolean state;
    private Integer judge;
    private String text;
    private Integer result;

    private String contestTitle;
}
