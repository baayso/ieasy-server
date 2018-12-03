package com.baayso.springboot.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.baayso.commons.utils.JsonUtils;
import com.baayso.commons.utils.Validator;
import com.fasterxml.jackson.databind.JsonNode;
import com.gitee.easyopen.ApiContext;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

/**
 * EasyOpen通用业务类。
 *
 * @author ChenFangjie (2018/12/1 13:53)
 * @since 3.0.0
 */
public class CommonApi {

    @Inject
    protected Validator validator;


    /**
     * 获取 HTTP 请求。
     *
     * @return {@linkplain HttpServletRequest}
     *
     * @since 3.0.0
     */
    public static HttpServletRequest getRequest() {
        return ApiContext.getRequest();
    }

    /**
     * 获取 接入应用ID。
     *
     * @return 接入应用ID
     *
     * @since 3.0.0
     */
    public static String getAppKey() {
        return ApiContext.getApiParam().fatchAppKey();
    }

    /**
     * 使用给定的 JSON Schema 验证给定的字符串，如果符合要求返回true。
     *
     * @param schema JSON 模式
     * @param json   字符串
     *
     * @return 符合要求返回true，否则返回false
     *
     * @since 3.0.0
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
     * @since 3.0.0
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
     * @since 3.0.0
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
