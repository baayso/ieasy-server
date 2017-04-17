package com.baayso.springboot.access.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.baayso.commons.spring.SpringUtils;
import com.baayso.commons.utils.JsonUtils;
import com.baayso.springboot.access.business.AccessBusiness;
import com.baayso.springboot.access.domain.AccessDO;
import com.baayso.springboot.access.tool.AccessResponseStatus;
import com.baayso.springboot.common.controller.CommonController;
import com.baayso.springboot.common.exception.ApiServiceException;

/**
 * 接入方工具类。
 *
 * @author ChenFangjie (2017/4/17 14:32)
 * @since 1.0.0
 */
public final class AccessUtils {

    // 让工具类彻底不可以实例化
    private AccessUtils() {
        throw new Error("工具类不可以实例化！");
    }

    /**
     * 获取当前接入方。
     *
     * @return 接入方
     *
     * @since 1.0.0
     */
    public static AccessDO getCurrentAccess() {
        String token = CommonController.getAccessToken();

        if (StringUtils.isBlank(token)) {
            throw new ApiServiceException(AccessResponseStatus.MISSING_ACCESS_TOKEN);
        }

        StringRedisTemplate stringRedisTemplate = SpringUtils.getBean(StringRedisTemplate.class);

        String key = AccessBusiness.ACCESS_PREFIX + token;

        String access = stringRedisTemplate.opsForValue().get(key);

        if (StringUtils.isBlank(access)) {
            throw new ApiServiceException(AccessResponseStatus.INVALID_ACCESS_TOKEN);
        }

        return JsonUtils.INSTANCE.fromJson(access, AccessDO.class);
    }

}
