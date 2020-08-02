package com.baayso.springboot.common.domain;

import java.io.Serializable;

import com.baayso.commons.tool.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回给客户端的操作结果（转换成json格式后返回给客户端）。
 *
 * @author ChenFangjie (2016/4/11 16:17)
 * @since 1.0.0
 */
@Getter
@Setter
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -2741908447112918130L;

    private boolean status;     // 返回状态
    private Integer statusCode; // 返回编码
    private Object  message;    // 返回提示
    private T       data;       // 返回数据

    public ResultVO() {
    }

    public ResultVO(T data) {
        this(true, data);
    }

    public ResultVO(boolean status, int statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }

    public ResultVO(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResultVO(boolean status, int statusCode, T data) {
        this.status = status;
        this.statusCode = statusCode;
        this.data = data;
    }

    public ResultVO(ResponseStatus responseStatus, T data) {
        this(true, responseStatus, data);
    }

    public ResultVO(boolean status, ResponseStatus responseStatus, T data) {
        this.status = status;
        this.statusCode = responseStatus.value();
        this.message = responseStatus.getReason();
        this.data = data;
    }

    // =====================================================================

    public static <T> ResultVO<T> ok() {
        return new ResultVO<>(null);
    }

    public static <T> ResultVO<T> ok(T data) {
        return new ResultVO<>(data);
    }

    public static <T> ResultVO<T> ok(String message) {
        ResultVO<T> result = new ResultVO<>(true, null);
        result.setMessage(message);

        return result;
    }

    public static <T> ResultVO<T> ok(T data, String message) {
        ResultVO<T> result = new ResultVO<>(true, data);
        result.setMessage(message);

        return result;
    }

    // =====================================================================

    public static <T> ResultVO<T> error() {
        ResultVO<T> result = new ResultVO<>(false, null);
        result.setStatusCode(500);
        result.setMessage("服务器内部错误");

        return result;
    }

    public static <T> ResultVO<T> error(String message) {
        ResultVO<T> result = new ResultVO<>(false, null);
        result.setStatusCode(500);
        result.setMessage(message);

        return result;
    }

    public static <T> ResultVO<T> error(int statusCode, String message) {
        ResultVO<T> result = new ResultVO<>(false, null);
        result.setStatusCode(statusCode);
        result.setMessage(message);

        return result;
    }

}
