package com.baayso.springboot.common.dataway.interceptor;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.hasor.dataql.Finder;
import net.hasor.dataql.compiler.qil.QIL;
import net.hasor.dataway.spi.ApiInfo;
import net.hasor.dataway.spi.CompilerSpiListener;

/**
 * CompilerSpiListener 也叫做编译拦截器，DataQL 在真正执行查询之前调用。
 * <p>
 * 如果当 PreExecuteChainSpi 中已经通过 future.completed 或者 future.failed 处理了请求，那么就不会引发 CompilerSpiListener。
 * <p>
 * 编译拦截器的应用场景主要有两个：
 * <li>实现对 QIL 缓存（QIL 是 DataQL 查询编译之后的指令序列，它类似 Java 的 class 文件）</li>
 * <li>改写或替换 DataQL 查询脚本</li>
 *
 * @author ChenFangjie (2020/5/17 15:22)
 * @since 4.0.0
 */
public class QilCacheSpi implements CompilerSpiListener {

    private Map<String, QIL> menCache = new ConcurrentHashMap<>();

    @Override
    public QIL compiler(ApiInfo apiInfo, String query, Set<String> varNames, Finder finder) throws IOException {
        String apiPath = apiInfo.getApiPath();

        if (apiPath.startsWith("/api/maps")) {
            if (this.menCache.containsKey(apiPath)) {
                return this.menCache.get(apiPath);
            }
            QIL compiler = CompilerSpiListener.DEFAULT.compiler(apiInfo, query, varNames, finder);
            this.menCache.put(apiPath, compiler);

            return compiler;
        }

        // 改写 DataQL 查询，在所有DataQL 查询的前面都统一追加一个 hint
        // query = "hint XXXXX = true; " + query; // 增加一个 XXXXX hint

        return CompilerSpiListener.DEFAULT.compiler(apiInfo, query, varNames, finder);
    }

}
