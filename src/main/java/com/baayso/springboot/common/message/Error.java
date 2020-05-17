package com.baayso.springboot.common.message;

/**
 * 定义错误返回
 * @author tanghc (https://gitee.com/durcframework/easyopen)
 *
 * @param <T> 状态码类型，一般为Integer或String
 */
public interface Error<T> {
    /**
     * 获取错误信息
     * @return 返回错误信息
     */
    String getMsg();

    /**
     * 获取状态码
     * @return 返回状态码
     */
    T getCode();
}
