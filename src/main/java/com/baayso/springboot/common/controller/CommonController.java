package com.baayso.springboot.common.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import com.baayso.commons.utils.Validator;
import com.baayso.commons.web.util.WebUtils;

/**
 * 通用控制器。
 *
 * @author ChenFangjie (2016/12/12 14:43)
 * @since 1.0.0
 */
public class CommonController {

    /** 默认页码 */
    protected static final String DEFAULT_PAGE_NUM  = "1";
    /** 默认每页记录数 */
    protected static final String DEFAULT_PAGE_SIZE = "10";

    @Inject
    protected Validator validator;


    /**
     * 从 HTTP Request 中获取 accessToken（API访问凭证）。
     *
     * @return API访问凭证
     *
     * @since 1.0.0
     */
    public static String getAccessToken() {
        return WebUtils.getRequest().getParameter("accessToken");
    }

    /**
     * 从 HTTP Request 中获取 userToken（用户凭证）。
     *
     * @return 用户凭证
     *
     * @since 1.0.0
     */
    public static String getUserToken() {
        return WebUtils.getRequest().getParameter("userToken");
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
