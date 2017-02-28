package com.baayso.springboot.access.service;

import javax.inject.Inject;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baayso.springboot.access.domain.AccessDO;
import com.baayso.springboot.common.service.AbstractCommonService;


/**
 * 接入方通用业务逻辑。
 *
 * @author ChenFangjie (2016/4/13 10:57)
 * @since 1.0.0
 */
@Service
public class AccessService extends AbstractCommonService<AccessDO, Long> {

    @Inject
    private RedisTemplate<String, Object> redisTemplate;


    public AccessDO getByCache(String accessKey) {
        AccessDO access = (AccessDO) this.redisTemplate.opsForValue().get(accessKey);

        if (access == null) {
            access = super.get(AccessDO.builder().accessKey(accessKey).build());

            // 写入缓存
            if (access != null) {
                this.redisTemplate.opsForValue().set(accessKey, access);
            }
        }

        return access;
    }

}
