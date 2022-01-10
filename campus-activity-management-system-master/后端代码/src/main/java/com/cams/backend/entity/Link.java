package com.cams.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cams
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer uid;

    private Integer aid;

    /**
     * 报名表编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String uname;

    private String aname;


}
