package com.baayso.springboot.common.dataway.interceptor;

import java.util.HashMap;

import net.hasor.dataway.spi.ApiInfo;
import net.hasor.dataway.spi.ResultProcessChainSpi;

/**
 * Dataway ResultProcess拦截器。
 * 一个已经发布的接口被调用之后，一定会触发这个拦截器。
 *
 * @author ChenFangjie (2020/5/17 14:57)
 * @since 4.0.0
 */
public class MyResultProcessChainSpi implements ResultProcessChainSpi {

    // 结果拦截，用于处理 Query 正确执行之后的二次结果处理。
    @Override
    public Object callAfter(boolean formPre, ApiInfo apiInfo, Object result) {
        // 响应结果改写
        return new HashMap<String, Object>(3) {{
            put("method", apiInfo.getMethod());
            put("path", apiInfo.getApiPath());
            put("result", result);
        }};
    }

    // 异常拦截，当 Query 执行发生异常时。
    // 异常拦截器十分强大，除了 DataQL 执行异常之外，它还能拦截 PreExecuteChainSpi 的异常。
    // 甚至它还可以拦截自己 callAfter 过程引发的异常。
    @Override
    public Object callError(boolean formPre, ApiInfo apiInfo, Throwable e) {
        // 异常统一处理
        return new HashMap<String, Object>(3) {{
            put("method", apiInfo.getMethod());
            put("path", apiInfo.getApiPath());
            put("errorMessage", e.getMessage());
        }};
    }

}
