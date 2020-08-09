package com.baayso.springboot.common.exception;

import com.baayso.commons.exception.ApiException;
import com.baayso.commons.tool.ResponseStatus;

/**
 * 专用于 API 校验的异常。
 *
 * @author ChenFangjie (2017/06/02 10:07)
 * @since 1.0.0
 */
public class ApiViolationException extends ApiException {

    private static final long serialVersionUID = -3736694702255244323L;

    public ApiViolationException() {
    }

    public ApiViolationException(String message) {
        super(message);
    }

    public ApiViolationException(ResponseStatus responseStatus) {
        super(responseStatus);
    }

    public ApiViolationException(int code, String message) {
        super(code, message);
    }

    public ApiViolationException(ResponseStatus responseStatus, String message) {
        super(responseStatus, message);
    }

}
