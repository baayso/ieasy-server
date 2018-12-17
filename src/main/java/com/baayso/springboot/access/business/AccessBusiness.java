package com.baayso.springboot.access.business;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.baayso.commons.utils.JsonUtils;
import com.baayso.springboot.access.domain.AccessApiDO;
import com.baayso.springboot.access.domain.AccessDO;
import com.baayso.springboot.access.domain.TokenVO;
import com.baayso.springboot.access.service.AccessApiService;
import com.baayso.springboot.access.service.AccessService;
import com.baayso.springboot.access.tool.AccessResponseStatus;
import com.baayso.springboot.common.exception.ApiServiceException;

/**
 * 接入方复杂业务逻辑。
 *
 * @author ChenFangjie (2016/4/18 10:42)
 * @since 1.0.0
 */
@Service
public class AccessBusiness {

    // 凭证有效时间（6个小时），单位：秒
    private final static long ACCESS_TOKEN_EXPIRES_IN = 21600L;

    /** access 前缀 */
    public final static String ACCESS_PREFIX       = "api.access:";
    /** access token 前缀 */
    public final static String ACCESS_TOKEN_PREFIX = "api.accessToken:";

    @Inject
    private StringRedisTemplate stringRedisTemplate;

    @Inject
    private AccessService accessService;

    @Inject
    private AccessApiService accessApiService;


    /**
     * 获取access token。
     *
     * @param accessKey    第三方用户唯一凭证
     * @param accessSecret 第三方用户唯一凭证密钥
     *
     * @return 获取到的凭证
     *
     * @since 1.0.0
     */
    public TokenVO getToken(String accessKey, String accessSecret) {
        if (StringUtils.isBlank(accessKey)) {
            throw new ApiServiceException(AccessResponseStatus.MISSING_ACCESS_KEY);
        }

        if (StringUtils.isBlank(accessSecret)) {
            throw new ApiServiceException(AccessResponseStatus.MISSING_ACCESS_SECRET);
        }

        AccessDO access = this.accessService.getByCache(accessKey);

        if (access == null) {
            throw new ApiServiceException(AccessResponseStatus.INVALID_ACCESS_KEY);
        }

        if (Boolean.FALSE.equals(access.getEnabled())) {
            throw new ApiServiceException(AccessResponseStatus.ACCESS_KEY_DISABLED);
        }

        String secret = access.getAccessSecret();

        if (!StringUtils.equals(secret, accessSecret)) {
            throw new ApiServiceException(AccessResponseStatus.INVALID_ACCESS_SECRET);
        }

        // 查询接入方所拥有的API列表
        List<AccessApiDO> apis = this.accessApiService.listByAccessId(access.getId());
        if (apis != null && !apis.isEmpty()) {
            access.setApis(new HashSet<>(apis));
        }

        String accessToken = RandomStringUtils.randomAlphanumeric(16);

        // 写入缓存
        this.stringRedisTemplate.opsForValue().set(ACCESS_PREFIX + accessToken, //
                JsonUtils.INSTANCE.toJson(access), //
                ACCESS_TOKEN_EXPIRES_IN, //
                TimeUnit.SECONDS);
        this.stringRedisTemplate.opsForValue().set(ACCESS_TOKEN_PREFIX + accessToken,
                accessToken, //
                ACCESS_TOKEN_EXPIRES_IN, //
                TimeUnit.SECONDS);

        return TokenVO.builder().accessToken(accessToken).expiresIn(ACCESS_TOKEN_EXPIRES_IN).build();
    }

}
