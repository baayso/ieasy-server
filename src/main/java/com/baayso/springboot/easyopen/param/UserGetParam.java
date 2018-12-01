package com.baayso.springboot.easyopen.param;

import com.gitee.easyopen.doc.DataType;
import com.gitee.easyopen.doc.annotation.ApiDocField;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务参数：获取用户。
 *
 * @author ChenFangjie (2018/11/29 16:14)
 * @since 3.0.0
 */
@Getter
@Setter
public class UserGetParam {

    @ApiDocField(description = "用户ID", dataType = DataType.LONG, required = true, example = "1")
    private String id;

}
