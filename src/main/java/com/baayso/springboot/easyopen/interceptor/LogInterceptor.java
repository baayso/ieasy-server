package com.baayso.springboot.easyopen.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.baayso.commons.log.Log;
import com.baayso.commons.utils.JsonUtils;
import com.gitee.easyopen.interceptor.ApiInterceptorAdapter;
import com.gitee.easyopen.util.RequestUtil;

/**
 * EasyOpen拦截器：日志处理。
 * <p>
 * https://durcframework.gitee.io/easyopen/#/guide?id=%E9%85%8D%E7%BD%AE%E6%8B%A6%E6%88%AA%E5%99%A8
 * <p>
 * 默认拦截所有接口，如果要拦截指定接口，可重写 public boolean match() 方法。
 *
 * @since 3.0.0
 */
public class LogInterceptor extends ApiInterceptorAdapter {

    private static final Logger log = Log.get();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object serviceObj, Object arg) throws Exception {

        log.warn("======preHandle======");

        log.warn("IP: {}", RequestUtil.getClientIP(request));
        log.warn("接口类: {}", serviceObj.getClass().getName());
        if (arg != null) {
            log.warn("参数类: {}", arg.getClass().getName());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object serviceObj, Object arg, Object result) throws Exception {

        log.warn("======postHandle======");

        log.warn("接口类: {}", serviceObj.getClass().getName());
        if (arg != null) {
            log.warn("参数类: {}", arg.getClass().getName());
        }
        log.warn("结果: {}", JsonUtils.INSTANCE.toJson(result));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object serviceObj, Object arg, Object result, Exception e) throws Exception {

        log.warn("======afterCompletion======");

        log.warn("接口类: {}", serviceObj.getClass().getName());
        if (arg != null) {
            log.warn("参数类: {}", arg.getClass().getName());
        }
        log.warn("最终结果: {}", JsonUtils.INSTANCE.toJson(result));
        log.warn("e: {}", e);
    }

}