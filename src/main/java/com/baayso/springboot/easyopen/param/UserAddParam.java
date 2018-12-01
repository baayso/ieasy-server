package com.baayso.springboot.easyopen.param;

import com.baayso.springboot.demo.domain.enums.OrderStatus;
import com.gitee.easyopen.doc.DataType;
import com.gitee.easyopen.doc.annotation.ApiDocField;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务参数：添加用户。
 *
 * @author ChenFangjie (2018/11/29 16:14)
 * @since 3.0.0
 */
@Getter
@Setter
public class UserAddParam {

    @ApiDocField(description = "用户名称", required = true, example = "小毛驴")
    private String name;

    @ApiDocField(description = "用户年龄", dataType = DataType.INT, required = true, example = "22")
    private String age;

    @ApiDocField(description = "状态", dataType = DataType.INT, enumClass = OrderStatus.class, required = true, example = "512")
    private String status;

    @ApiDocField(description = "用户简介", example = "哈哈哈")
    private String intro;

}
