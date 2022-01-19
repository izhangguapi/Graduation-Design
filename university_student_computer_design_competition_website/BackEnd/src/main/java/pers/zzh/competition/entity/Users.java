package pers.zzh.competition.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Users {
    @TableId
    private Integer userId;
    private String name;
    private Boolean sex;
    private String birthday;
    private String school;
    private String address;
    private String phone;
    private String email;
    private Integer groupId;
    private String password;
    //链表查询-组表字段
    @TableField(exist = false)
    private String groupName;
    @TableField(exist = false)
    private String encoding;
}
