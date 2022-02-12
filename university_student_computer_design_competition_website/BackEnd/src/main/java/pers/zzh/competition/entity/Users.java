package pers.zzh.competition.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users {
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
    private String groupName;
    private String encoding;
}
