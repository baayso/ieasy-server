package com.baayso.springboot.common.exception;

import com.baayso.commons.tool.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * 专用于 API Service层的异常。
 *
 * @author ChenFangjie (2014/12/20 16:39:58)
 * @since 1.0.0
 */
@Slf4j
public class ApiServiceException extends RuntimeException {

    private static final long serialVersionUID = -3247721709918992766L;

    public ResponseStatus responseStatus;

    public ApiServiceException() {
    }

    public ApiServiceException(ResponseStatus responseStatus) {
        super(responseStatus.getReason());
        this.responseStatus = responseStatus;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        if (log.isDebugEnabled()) {
            return super.fillInStackTrace();
        }

        return null;
    }

}
