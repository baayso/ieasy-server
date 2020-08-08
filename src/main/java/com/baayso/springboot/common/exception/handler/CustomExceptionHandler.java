package com.baayso.springboot.common.exception.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.baayso.commons.exception.ApiException;
import com.baayso.commons.tool.BasicResponseStatus;
import com.baayso.commons.tool.ResponseStatus;
import com.baayso.commons.utils.JsonUtils;
import com.baayso.commons.web.WebUtils;
import com.baayso.springboot.common.domain.ResultVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理器。
 *
 * @author ChenFangjie
 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
 * @since 1.0.0
 */
@Slf4j
@RestControllerAdvice
public final class CustomExceptionHandler {

    /**
     * 处理ApiException异常。
     *
     * @since 1.0.0
     */
    @ExceptionHandler(value = {ApiException.class})
    public ResultVO<String> handleApiServiceException(ApiException ex, HttpServletRequest request) {
        return this.handleException(ex.getResponseStatus(), ex, request);
    }

    /**
     * 处理Exception异常。
     *
     * @since 1.0.0
     */
    @ExceptionHandler(value = {Exception.class})
    public ResultVO<String> handleGeneralException(Exception ex, HttpServletRequest request) {
        return this.handleException(BasicResponseStatus.SERVER_INTERNAL_ERROR, ex, request);
    }

    /**
     * 处理缺少请求参数异常。
     *
     * @since 4.0.0
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResultVO<String> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                 HttpServletRequest request) {
        return this.handleException(BasicResponseStatus.PARAMETER_MISSING, ex, request);
    }

    /**
     * 处理请求参数类型不匹配异常。
     *
     * @since 4.0.0
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResultVO<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                             HttpServletRequest request) {
        return this.handleException(BasicResponseStatus.PARAMETER_TYPE_ERROR, ex, request);
    }

    /**
     * 处理请求method不匹配异常。
     *
     * @since 4.0.0
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResultVO<String> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                HttpServletRequest request) {
        return this.handleException(BasicResponseStatus.METHOD_NOT_ALLOWED, ex, request);
    }

    /**
     * 处理Controller方法中@RequestBody类型参数数据类型转换异常。
     *
     * @since 4.0.0
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResultVO<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                         HttpServletRequest request) {
        return this.handleException(BasicResponseStatus.REQUEST_BODY_DATA_CONVERT_ERROR, ex, request);
    }

    /**
     * 处理Controller方法参数校验异常。
     *
     * @since 4.0.0
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResultVO<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                         HttpServletRequest request) {
        // 注入ServletRequest，用于出错时打印请求URL与来源地址
        this.logError(ex, request);

        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);

        ResultVO<String> result = new ResultVO<>();
        result.setSuccess(false);
        result.setCode(BasicResponseStatus.PARAMETER_CHECK_FAILED.value());
        result.setMessage(BasicResponseStatus.PARAMETER_CHECK_FAILED.getReason());
        result.setData(objectError.getDefaultMessage()); // 错误提示信息

        return result;
    }

    private ResultVO<String> handleException(ResponseStatus responseStatus, Exception ex, HttpServletRequest request) {
        // 注入ServletRequest，用于出错时打印请求URL与来源地址
        this.logError(ex, request);

        ResultVO<String> result = new ResultVO<>();
        result.setSuccess(false);
        result.setCode(responseStatus.value());
        result.setMessage(responseStatus.getReason());
        result.setData(ex.getMessage()); // 错误提示信息

        return result;
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
