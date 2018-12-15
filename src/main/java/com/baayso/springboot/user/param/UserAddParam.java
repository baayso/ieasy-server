package com.baayso.springboot.user.param;

import com.gitee.easyopen.doc.annotation.ApiDocField;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务参数：添加用户。
 *
 * @author ChenFangjie (2018/12/15 22:30)
 * @since 3.0.0
 */
@Getter
@Setter
public class UserAddParam {

    @ApiDocField(description = "用户名", required = true, example = "admin")
    private String username;

    @ApiDocField(description = "真实姓名", required = true, example = "小毛驴")
    private String name;

    @ApiDocField(description = "密码", required = true, example = "123456")
    private String password;

    @ApiDocField(description = "手机号码", required = true, example = "13712345678")
    private String phone;

    @ApiDocField(description = "邮箱", required = true, example = "i@baayso.com")
    private String email;

}
