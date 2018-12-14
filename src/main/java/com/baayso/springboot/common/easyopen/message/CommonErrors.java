package com.baayso.springboot.common.easyopen.message;

import com.gitee.easyopen.message.ErrorMeta;

/**
 * 公共异常消息。
 *
 * @author ChenFangjie (2018/11/29 15:20)
 * @since 3.0.0
 */
public interface CommonErrors {

    String isvModule = "isv.error_"; // error_zh_CN2.properties内容前缀

    ErrorMeta BLANK_ERROR = new ErrorMeta(isvModule, "101", "不能为空");
    ErrorMeta ID_ERROR    = new ErrorMeta(isvModule, "102", "id不是一个有效的值");

}