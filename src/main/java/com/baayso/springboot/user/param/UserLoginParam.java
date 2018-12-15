package com.baayso.springboot.user.param;

import com.gitee.easyopen.doc.annotation.ApiDocField;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务参数：用户登录。
 *
 * @author ChenFangjie (2018/12/15 22:39)
 * @since 3.0.0
 */
@Getter
@Setter
public class UserLoginParam {

    @ApiDocField(description = "用户名", required = true, example = "admin")
    private String username;

    @ApiDocField(description = "密码", example = "123456")
    private String password;

}
