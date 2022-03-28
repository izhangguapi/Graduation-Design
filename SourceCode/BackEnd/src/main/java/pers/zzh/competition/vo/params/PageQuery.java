package pers.zzh.competition.vo.params;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author zhangguapi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageQuery {
    private String query;
    private Integer currentPage;
    private Integer pageSize;
}
