package com.baayso.springboot.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.baayso.commons.tool.BasicResponseStatus;
import com.baayso.springboot.common.exception.ApiViolationException;

/**
 * 验证租户参数拦截器。
 *
 * @author ChenFangjie (2020/8/9 12:37)
 * @since 4.0.0
 */
public class TenantInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String tenantId = request.getHeader("tenantId");

        if (StringUtils.isBlank(tenantId)) {
            throw new ApiViolationException(BasicResponseStatus.PARAMETER_MISSING, "缺少tenantId参数");
        }

        try {
            Long.parseLong(tenantId);
        }
        catch (Exception e) {
            throw new ApiViolationException(BasicResponseStatus.PARAMETER_TYPE_ERROR, "tenantId参数类型不匹配");
        }

        return super.preHandle(request, response, handler);
    }

}
