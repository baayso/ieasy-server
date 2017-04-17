package com.baayso.springboot.access.web.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.baayso.springboot.access.domain.AccessApiDO;
import com.baayso.springboot.access.domain.AccessDO;
import com.baayso.springboot.access.tool.AccessResponseStatus;
import com.baayso.springboot.access.utils.AccessUtils;
import com.baayso.springboot.common.exception.ApiServiceException;

/**
 * 检查API权限拦截器。
 *
 * @author ChenFangjie (2017/4/17 14:18)
 * @since 1.0.0
 */
public class AccessApiAuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AccessDO access = AccessUtils.getCurrentAccess();

        Set<AccessApiDO> apis = access.getApis();

        if (apis != null && !apis.isEmpty()) {
            String url = request.getRequestURI();

            for (AccessApiDO api : apis) {
                if (url.endsWith(api.getUrl())) {
                    return true;
                }
            }
        }

        response.setHeader("Access-Control-Allow-Origin", "*"); // CORS

        throw new ApiServiceException(AccessResponseStatus.PERMISSION_DENIED);
    }

}
