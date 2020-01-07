package com.baayso.springboot.common.utils;

import java.util.UUID;

import com.baayso.commons.sequence.mongo.ObjectId;

/**
 * ID生成工具类。
 *
 * @author ChenFangjie (2016/5/5 9:26)
 * @since 1.0.0
 */
public final class IdUtils {

    // 让工具类彻底不可以实例化
    private IdUtils() {
        throw new Error("工具类不可以实例化！");
    }

    /**
     * 获取uuid，使用jdk自带的uuid，通过Random数字生成，中间无-分割。
     *
     * @return uuid
     *
     * @since 1.0.0
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取MongoDB ObjectId（十六进制形式）。
     *
     * @return ObjectId（十六进制形式）
     *
     * @since 3.1.0
     */
    public static String objectId() {
        return ObjectId.get().toString();
    }

}
