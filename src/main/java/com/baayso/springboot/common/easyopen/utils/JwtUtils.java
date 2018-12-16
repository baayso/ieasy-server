package com.baayso.springboot.common.easyopen.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.auth0.jwt.interfaces.Claim;
import com.baayso.springboot.common.easyopen.message.CommonErrors;
import com.gitee.easyopen.ApiContext;

/**
 * JSON Web Token（JWT）工具类。
 * <p>
 * EasyOpen中使用JWT: https://durcframework.gitee.io/easyopen/#/guide?id=%E4%BD%BF%E7%94%A8jwt
 *
 * @author ChenFangjie (2018/12/16 19:44)
 * @since 3.0.0
 */
public class JwtUtils {

    /**
     * 生成JWT。
     *
     * @param userId   用户ID
     * @param username 用户名
     *
     * @return JSON Web Token（JWT）
     *
     * @since 3.0.0
     */
    public static String createToken(String userId, String username) {

        Map<String, String> data = new HashMap<>(2);
        data.put("id", userId);
        data.put("username", username);

        return ApiContext.createJwt(data);

    }

    /**
     * 获取JWT数据。
     *
     * @return jwt data.
     *
     * @since 3.0.0
     */
    public static Map<String, Claim> getJwtData() {

        Map<String, Claim> jwtData = ApiContext.getJwtData();

        if (jwtData == null) {
            throw CommonErrors.GET_JWT_ERROR.getException();
        }

        return jwtData;
    }

    /**
     * 获取用户ID。
     *
     * @return 用户ID
     *
     * @since 3.0.0
     */
    public static Long getUserId() {
        Map<String, Claim> jwtData = getJwtData();

        Claim userId = jwtData.get("id");

        if (userId == null || StringUtils.isBlank(userId.asString())) {
            throw CommonErrors.GET_JWT_ERROR.getException();
        }

        return Long.valueOf(userId.asString());
    }

    /**
     * 获取用户名。
     *
     * @return 用户名
     *
     * @since 3.0.0
     */
    public static String getUsername() {
        Map<String, Claim> jwtData = getJwtData();

        Claim username = jwtData.get("username");

        if (username == null || StringUtils.isBlank(username.asString())) {
            throw CommonErrors.GET_JWT_ERROR.getException();
        }

        return username.asString();
    }

}
