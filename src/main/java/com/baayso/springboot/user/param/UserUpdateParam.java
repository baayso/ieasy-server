package com.baayso.springboot.user.param;

import com.gitee.easyopen.doc.DataType;
import com.gitee.easyopen.doc.annotation.ApiDocField;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务参数：更新用户。
 *
 * @author ChenFangjie (2018/12/17 16:36)
 * @since 3.0.0
 */
@Getter
@Setter
public class UserUpdateParam {

    @ApiDocField(description = "用户ID", dataType = DataType.LONG, required = true, example = "123")
    private String id;

    @ApiDocField(description = "姓名", example = "小小毛驴")
    private String name;

    @ApiDocField(description = "手机号码", example = "13712345678")
    private String phone;

    @ApiDocField(description = "邮箱", example = "i@baayso.com")
    private String email;

}
