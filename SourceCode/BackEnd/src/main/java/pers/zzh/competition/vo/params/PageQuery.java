package pers.zzh.competition.vo.params;

import com.baomidou.mybatisplus.annotation.TableField;
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
    @TableField(exist = false)
    private String query;
    @TableField(exist = false)
    private Integer currentPage;
    @TableField(exist = false)
    private Integer pageSize;
}
