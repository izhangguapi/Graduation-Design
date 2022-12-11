package pers.zzh.competition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.zzh.competition.vo.params.PageQuery;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users extends PageQuery {
    @TableId
    private Integer userId;
    private String name;
    private Boolean sex;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    private String school;
    private String address;
    private String phone;
    private String email;
    private Integer groupId;
    private String password;
    // 链表查询-组表字段：组名、组编码
    @TableField(exist = false)
    private String groupName;
    @TableField(exist = false)
    private String encoding;

    public String getSex() {
        return sex ? "男" : "女";
    }

    public void setSex(String sex) {
        if (sex.equals("男")) {
            this.sex = true;
        } else if (sex.equals("女")) {
            this.sex = false;
        }
    }
}
