package com.baayso.springboot.common.exception.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springside.modules.web.MediaTypes;

import com.baayso.commons.exception.ApiException;
import com.baayso.commons.tool.CommonResponseStatus;
import com.baayso.commons.tool.ResponseStatus;
import com.baayso.commons.web.WebUtils;
import com.baayso.springboot.common.exception.ApiServiceException;
import com.baayso.springboot.common.tool.OperationResult;
import com.baayso.springboot.common.utils.JsonUtils;
import com.baayso.springboot.common.validator.BeanValidators;

import lombok.extern.slf4j.Slf4j;

/**
 * 异常处理器。
 *
 * @author ChenFangjie
 * @since 1.0.0
 */
@Slf4j
@ControllerAdvice(annotations = {RestController.class})
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 处理ApiServiceException异常。
     *
     * @since 1.0.0
     */
    @ExceptionHandler(value = {ApiServiceException.class})
    public final ResponseEntity<OperationResult> handleApiServiceException(ApiServiceException ex, HttpServletRequest request) {
        // 注入servletRequest，用于出错时打印请求URL与来源地址
        logError(ex, request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));

        OperationResult result = new OperationResult(false, ex.responseStatus.value());
        result.setMessage(ex.responseStatus.getReason());

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    /**
     * 处理Exception异常。
     *
     * @since 1.0.0
     */
    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<OperationResult> handleGeneralException(Exception ex, HttpServletRequest request) {
        logError(ex, request);

        ResponseStatus status = CommonResponseStatus.UNKNOWN_ERROR;

        if (log.isDebugEnabled()) {
            if (ex instanceof ApiException) {
                ApiException exception = (ApiException) ex;
                status = exception.responseStatus;
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));

        OperationResult result = new OperationResult(false, status.value());
        result.setMessage(status.getReason());

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    /**
     * 处理JSR349 Validation异常。
     *
     * @since 1.0.0
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public final ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        Map<String, String> errors = BeanValidators.extractPropertyAndMessage(ex.getConstraintViolations());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));

        OperationResult result = new OperationResult(false, CommonResponseStatus.ILLEGAL_DATA.value());
        result.setMessage(errors);

        return super.handleExceptionInternal(ex, result, headers, HttpStatus.OK, request);
    }

    /**
     * 记录错误日志。
     *
     * @since 1.0.0
     */
    public void logError(Exception ex) {
        Map<String, String> map = new HashMap<>(1);
        map.put("message", ex.getMessage());

        log.error(JsonUtils.INSTANCE.toJson(map), ex);
    }

    /**
     * 记录与http相关的错误日志。
     *
     * @since 1.0.0
     */
    public void logError(Exception ex, HttpServletRequest request) {
        String queryString = request.getQueryString();

        Map<String, String> map = new HashMap<>(3);
        map.put("message", ex.getMessage());
        map.put("from", WebUtils.getRealIp(request));
        map.put("path", StringUtils.isNotBlank(queryString) ? (request.getRequestURI() + "?" + queryString) : request.getRequestURI());

        log.error(JsonUtils.INSTANCE.toJson(map), ex);
    }

}
