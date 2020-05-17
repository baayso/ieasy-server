package com.baayso.springboot.common.dataway.interceptor;

import net.hasor.dataway.spi.ApiInfo;
import net.hasor.dataway.spi.PreExecuteChainSpi;
import net.hasor.dataway.spi.StatusMessageException;
import net.hasor.utils.future.BasicFuture;

/**
 * Dataway PreExecute拦截器。
 * <p>
 * 当一个API发起调用之后，可以通过API请求拦截器处理一些特殊的逻辑。比方说下面这些场景：
 * <li>所有 API 请求都加入某个固定的参数</li>
 * <li>通过拦截器实现接口权限控制</li>
 * <li>配合 ResultProcessChainSpi 对接口进行结果缓存。</li>
 *
 * @author ChenFangjie (2020/5/17 14:57)
 * @since 4.0.0
 */
public class MyPreExecuteChainSpi implements PreExecuteChainSpi {

    @Override
    public void preExecute(ApiInfo apiInfo, BasicFuture<Object> future) {
        // 所有 API 请求都加入某个固定的参数
        apiInfo.getParameterMap().put("self", "me");

        // 没有权限抛出异常
        String apiPath = apiInfo.getApiPath();
        String apiMethod = apiInfo.getMethod();
        if (false) {
            // （方式1）通过 future 设置异常信息
            future.failed(new StatusMessageException(401, "not power"));
            // （方式2）或者直接 throw 一个异常
            throw new StatusMessageException(401, "not power");
        }

        // 返回预先准备好的数据
        if (false) {
            future.completed("...");
        }
    }

}
