package com.baayso.springboot.user.domain;

import java.time.LocalDateTime;

import com.baayso.springboot.common.domain.BaseDO;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitee.easyopen.doc.annotation.ApiDocField;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 数据对象：用户。
 *
 * @author ChenFangjie (2018/12/14 20:12)
 * @since 3.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("user")
public class UserDO extends BaseDO {

    private static final long serialVersionUID = -2675694222252892281L;

    @ApiDocField(description = "用户名")
    private String username;

    @ApiDocField(description = "真实姓名")
    private String name;

    @ApiDocField(description = "密码")
    private String password;

    @JsonIgnore
    private String salt;

    @JsonIgnore
    private Integer type;

    @ApiDocField(description = "手机号码")
    private String phone;

    @ApiDocField(description = "邮箱")
    private String email;

    @JsonIgnore
    private Boolean isLock;

    @JsonIgnore
    private Boolean isDisable;

    @JsonIgnore
    @TableLogic
    private Boolean isDelete;


    @Builder
    public UserDO(Long id, String createBy, LocalDateTime createTime, String modifyBy, LocalDateTime modifyTime,
                  String username, String name, String password, String salt, Integer type, String phone, String email,
                  Boolean isLock, Boolean isDisable, Boolean isDelete) {

        super(id, createBy, createTime, modifyBy, modifyTime);

        this.username = username;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.type = type;
        this.phone = phone;
        this.email = email;
        this.isLock = isLock;
        this.isDisable = isDisable;
        this.isDelete = isDelete;
    }

}
