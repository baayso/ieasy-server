package com.baayso.springboot.common.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baayso.commons.utils.JsonUtils;
import com.baayso.commons.utils.Validator;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

/**
 * 通用控制器。
 *
 * @author ChenFangjie (2016/12/12 14:43)
 * @since 1.0.0
 */
public class CommonController {

    @Inject
    protected Validator validator;


    /**
     * 获取 HTTP 请求。
     *
     * @return {@linkplain HttpServletRequest}
     *
     * @since 1.0.0
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取 HTTP 响应。
     *
     * @return {@linkplain HttpServletResponse}
     *
     * @since 1.0.0
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 从 HTTP Request 中获取 accessToken（API访问凭证）。
     *
     * @return API访问凭证
     *
     * @since 1.0.0
     */
    public static String getAccessToken() {
        return getRequest().getParameter("accessToken");
    }

    /**
     * 从 HTTP Request 中获取 userToken（用户凭证）。
     *
     * @return 用户凭证
     *
     * @since 1.0.0
     */
    public static String getUserToken() {
        return getRequest().getParameter("userToken");
    }

    /**
     * 使用给定的 JSON Schema 验证给定的字符串，如果符合要求返回true。
     *
     * @param schema JSON 模式
     * @param json   字符串
     *
     * @return 符合要求返回true，否则返回false
     *
     * @since 1.0.0
     */
    public boolean isJson(JsonNode schema, String json) {
        try {
            JsonNode data = JsonUtils.INSTANCE.getMapper().readTree(json);

            ProcessingReport report = JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schema, data);

            return report.isSuccess();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将字符串数组转换成长整型数组。
     *
     * @param array 字符串数组
     *
     * @return 长整型数组，如果字符串数组中包含非数字则返回长度为0的长整型数组
     *
     * @since 1.0.0
     */
    public Long[] strArr2LongArr(String[] array) {
        Long[] longs = new Long[array.length];

        for (int i = 0; i < array.length; i++) {
            String element = array[i];

            if (this.validator.isLong(element)) {
                longs[i] = Long.parseLong(element);
            }
            else {
                return new Long[0];
            }
        }

        return longs;
    }

    /**
     * 将字符串数组转换成长整型列表。
     *
     * @param array 字符串数组
     *
     * @return 长整型列表，如果字符串数组中包含非数字则返回 {@linkplain Collections#emptyList()}
     *
     * @since 1.0.0
     */
    public List<Long> strArr2LongList(String[] array) {
        List<Long> list = new ArrayList<>(array.length);

        for (int i = 0; i < array.length; i++) {
            String element = array[i];

            if (this.validator.isLong(element)) {
                list.add(Long.parseLong(element));
            }
            else {
                return Collections.emptyList();
            }
        }

        return list;
    }

}
