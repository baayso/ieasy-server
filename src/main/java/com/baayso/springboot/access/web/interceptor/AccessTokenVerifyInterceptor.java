package com.baayso.springboot.access.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.baayso.commons.spring.SpringUtils;
import com.baayso.springboot.access.business.AccessBusiness;
import com.baayso.springboot.access.tool.AccessResponseStatus;
import com.baayso.springboot.common.exception.ApiServiceException;

/**
 * 验证 accessToken 拦截器。
 *
 * @author ChenFangjie (2016/4/13 14:38)
 * @since 1.0.0
 */
public class AccessTokenVerifyInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getParameter("accessToken");

        if (StringUtils.isBlank(accessToken)) {
            throw new ApiServiceException(AccessResponseStatus.MISSING_ACCESS_TOKEN);
        }

        StringRedisTemplate stringRedisTemplate = SpringUtils.getBean(StringRedisTemplate.class);

        String key = AccessBusiness.ACCESS_TOKEN_KEY_PREFIX + accessToken;

        String accessTokenOfCaching = stringRedisTemplate.opsForValue().get(key);

        if (StringUtils.isBlank(accessTokenOfCaching) || !StringUtils.equals(accessToken, accessTokenOfCaching)) {
            throw new ApiServiceException(AccessResponseStatus.INVALID_ACCESS_TOKEN);
        }

        return true;
    }

}
