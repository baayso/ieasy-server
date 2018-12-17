package com.baayso.springboot.user.message;

import com.gitee.easyopen.message.ErrorMeta;

/**
 * 用户模块异常消息。
 *
 * @author ChenFangjie (2018/12/14 22:13)
 * @since 3.0.0
 */
public interface UserErrors {

    String isvModule = "isv.user_error_"; // user_error_zh_CN.properties内容的前缀

    // 101为前缀后面的错误码，这句话即可找到isv.user_error_101错误
    ErrorMeta USER_NAME_ERROR    = new ErrorMeta(isvModule, "101", "用户名必须是字母、数字或者下划线的组合且长度必须大于等于{0}且小于等于{1}");
    ErrorMeta NAME_ERROR         = new ErrorMeta(isvModule, "102", "姓名必须是中文、字母、下划线、中划线或者数字的组合且长度必须大于等于{0}且小于等于{1}");
    ErrorMeta PASSWORD_RAW_ERROR = new ErrorMeta(isvModule, "103", "密码不能有空格（包括制表符、换行符）、百分号（%）、下划线（_）且长度必须大于等于{0}且小于等于{1}");
    ErrorMeta PHONE_ERROR        = new ErrorMeta(isvModule, "104", "手机号码必须就11位数字");
    ErrorMeta EMAIL_ERROR        = new ErrorMeta(isvModule, "105", "邮箱地址不合法");

    ErrorMeta NOT_FOUND      = new ErrorMeta(isvModule, "201", "用户不存在");
    ErrorMeta IS_LOCKED      = new ErrorMeta(isvModule, "202", "用户已被锁定");
    ErrorMeta IS_DISABLED    = new ErrorMeta(isvModule, "203", "用户已被禁用");
    ErrorMeta IS_DELETED     = new ErrorMeta(isvModule, "204", "用户已被删除");
    ErrorMeta PASSWORD_ERROR = new ErrorMeta(isvModule, "205", "用户名或密码不正确");

    ErrorMeta USER_NAME_USED = new ErrorMeta(isvModule, "301", "用户名已被使用");
    ErrorMeta PHONE_USED     = new ErrorMeta(isvModule, "302", "手机号码已被使用");
    ErrorMeta EMAIL_USED     = new ErrorMeta(isvModule, "303", "邮箱地址已被使用");

}
