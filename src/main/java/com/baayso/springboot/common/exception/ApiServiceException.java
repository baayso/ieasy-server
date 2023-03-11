package com.baayso.springboot.common.exception;

import com.baayso.commons.exception.ApiException;
import com.baayso.commons.tool.ResponseStatus;

/**
 * 专用于 API Service层的异常。
 *
 * @author ChenFangjie (2014/12/20 16:39:58)
 * @since 1.0.0
 */
public class ApiServiceException extends ApiException {

    private static final long serialVersionUID = 1L;

    public ApiServiceException() {
    }

    public ApiServiceException(String message) {
        super(message);
    }

    public ApiServiceException(String message, Object... args) {
        super(message, args);
    }

    public ApiServiceException(ResponseStatus responseStatus) {
        super(responseStatus);
    }

    public ApiServiceException(int code, String message) {
        super(code, message);
    }

    public ApiServiceException(int code, String message, Object... args) {
        super(code, message, args);
    }

    public ApiServiceException(ResponseStatus responseStatus, String message) {
        super(responseStatus, message);
    }

    public ApiServiceException(ResponseStatus responseStatus, String message, Object... args) {
        super(responseStatus, message, args);
    }

}
