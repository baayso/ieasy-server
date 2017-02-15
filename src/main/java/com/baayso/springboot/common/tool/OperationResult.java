package com.baayso.springboot.common.tool;

import java.io.Serializable;

import com.baayso.commons.tool.ResponseStatus;

import lombok.Data;

/**
 * 返回给客户端的操作结果（转换成json格式后返回给客户端）。
 *
 * @author ChenFangjie (2016/4/11 16:17)
 * @since 1.0.0
 */
@Data
public class OperationResult<T> implements Serializable {

    private static final long serialVersionUID = -2741908447112918130L;

    private boolean status;     // 返回状态
    private Integer statusCode; // 返回编码
    private Object  message;    // 返回提示
    private T       data;       // 返回数据

    public OperationResult() {
    }

    public OperationResult(boolean status, int statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }

    public OperationResult(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public OperationResult(boolean status, int statusCode, T data) {
        this.status = status;
        this.statusCode = statusCode;
        this.data = data;
    }

    public OperationResult(ResponseStatus responseStatus, T data) {
        this(true, responseStatus, data);
    }

    public OperationResult(boolean status, ResponseStatus responseStatus, T data) {
        this.status = status;
        this.statusCode = responseStatus.value();
        this.message = responseStatus.getReason();
        this.data = data;
    }

}
