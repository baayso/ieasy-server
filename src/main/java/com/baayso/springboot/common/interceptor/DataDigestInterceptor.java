package com.baayso.springboot.common.interceptor;

import com.baayso.commons.interceptor.DataDigestInterceptorAdapter;

/**
 * 验证数据摘要拦截器。
 *
 * @author ChenFangjie (2017/2/25 19:21)
 * @since 1.0.0
 */
public class DataDigestInterceptor extends DataDigestInterceptorAdapter {

    @Override
    protected String getSalt() {
        return "";
    }

}
