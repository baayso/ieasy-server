package com.baayso.springboot.easyopen.message;

import com.gitee.easyopen.message.ErrorMeta;

/**
 * 按模块来定义异常消息，团队开发可以分开进行。
 *
 * @author ChenFangjie (2018/11/29 15:21)
 * @since 3.0.0
 */
public interface UserErrors {

    String isvModule = "isv.user_error_"; // user_error_zh_CN.properties内容的前缀

    // 100为前缀后面的错误码，这句话即可找到isv.user_error_100错误
    ErrorMeta NO_USER_NAME = new ErrorMeta(isvModule, "100", "用户名称不能为空");

    ErrorMeta SHORT_USER_NAME   = new ErrorMeta(isvModule, "101", "用户名称太短");
    ErrorMeta USER_NAME_LENGTH  = new ErrorMeta(isvModule, "102", "用户名称的长度必须大于等于{0}且小于等于{1}");
    ErrorMeta USER_AGE_RANGE    = new ErrorMeta(isvModule, "103", "用户年龄的范围必须大于等于{0}且小于等于{1}");
    ErrorMeta USER_STATUS_ERROR = new ErrorMeta(isvModule, "104", "用户状态不是一个合法的值");
    ErrorMeta USER_INTRO_LENGTH = new ErrorMeta(isvModule, "105", "用户简介的长度必须大于等于{0}且小于等于{1}");

}
