package com.baayso.springboot.common.dataway.interceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.hasor.dataway.spi.ApiInfo;
import net.hasor.dataway.spi.PreExecuteChainSpi;
import net.hasor.dataway.spi.ResultProcessChainSpi;
import net.hasor.utils.future.BasicFuture;

/**
 * 实现调用缓存。
 *
 * @author ChenFangjie (2020/5/17 15:17)
 * @since 4.0.0
 */
public class ApiCacheSpi implements PreExecuteChainSpi, ResultProcessChainSpi {
    private Map<String, Object> cacheMap = new ConcurrentHashMap<>();

    @Override
    public void preExecute(ApiInfo apiInfo, BasicFuture<Object> future) {
        String cacheKey = "test_cache_key";
        if (this.cacheMap.containsKey(cacheKey)) {
            Object cacheValue = cacheMap.get(cacheKey);
            future.completed(cacheValue);
            return;
        }
    }

    @Override
    public Object callAfter(boolean formPre, ApiInfo apiInfo, Object result) {
        // formPre 为 true，表示 preExecute 已经处理过。
        // apiInfo.isPerform() 为 true 表示，API 调用是从 UI 界面发起的。
        if (formPre || apiInfo.isPerform()) {
            return result;
        }
        String cacheKey = "test_cache_key";
        this.cacheMap.put(cacheKey, result);
        return result;
    }

}
