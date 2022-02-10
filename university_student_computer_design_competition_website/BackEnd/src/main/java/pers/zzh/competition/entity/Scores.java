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
    private String contestant;
    private Boolean state;
    private String judge;
    private String text;
    private Integer result;
    //排名
    private int ranking;
    //比赛标题
    private String contestTitle;
}
