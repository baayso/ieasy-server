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

    ErrorMeta GET_JWT_ERROR  = new ErrorMeta(isvModule, "101", "获取JWT失败，请先登录");
    ErrorMeta ID_ERROR       = new ErrorMeta(isvModule, "102", "id不是一个有效的值");
    ErrorMeta NOTHING_UPDATE = new ErrorMeta(isvModule, "103", "未更新任何内容");

}