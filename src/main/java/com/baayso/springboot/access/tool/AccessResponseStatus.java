package com.baayso.springboot.access.tool;

import com.baayso.commons.tool.ResponseStatus;

/**
 * Access API Status Codes.
 *
 * @author ChenFangjie (2016/4/13 11:46)
 * @since 1.0.0
 */
public enum AccessResponseStatus implements ResponseStatus {

    INVALID_ACCESS_KEY(13010032, "不合法的accessKey，请检查accessKey的正确性，避免异常字符，注意大小写"),

    INVALID_ACCESS_SECRET(13010064, "不合法的accessSecret，请检查accessSecret的正确性，避免异常字符，注意大小写"),

    ACCESS_KEY_DISABLED(13010096, "accessKey已被禁用"),

    MISSING_ACCESS_TOKEN(13010128, "缺少accessToken参数"),

    INVALID_ACCESS_TOKEN(13010160, "不合法的accessToken，请认真比对accessToken的有效性"),

    MISSING_ACCESS_KEY(13010192, "缺少accessKey参数"),

    MISSING_ACCESS_SECRET(13010224, "缺少accessSecret参数"),

    PERMISSION_DENIED(13010256, "API权限不足");


    private final int    value;
    private final String reason;

    private AccessResponseStatus(int value, String reason) {
        this.value = value;
        this.reason = reason;
    }

    /**
     * Return the enum constant of this type with the specified numeric value.
     *
     * @param statusCode the numeric value of the enum to be returned
     *
     * @return the enum constant with the specified numeric value
     *
     * @throws IllegalArgumentException if this enum has no constant for the specified numeric value
     * @since 1.0.0
     */
    public static AccessResponseStatus valueOf(int statusCode) {
        for (AccessResponseStatus status : values()) {
            if (status.value == statusCode) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }

    /**
     * Return the integer value of this status code.
     */
    @Override
    public int value() {
        return this.value;
    }

    /**
     * Return the reason phrase of this status code.
     */
    @Override
    public String getReason() {
        return reason;
    }

    /**
     * Return a string representation of this status code.
     */
    @Override
    public String toString() {
        return Integer.toString(value);
    }

}
