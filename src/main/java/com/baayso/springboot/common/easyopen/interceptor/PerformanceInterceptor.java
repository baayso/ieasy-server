package com.baayso.springboot.common.easyopen.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.core.NamedThreadLocal;

import com.baayso.commons.log.Log;
import com.gitee.easyopen.interceptor.ApiInterceptorAdapter;

/**
 * EasyOpen拦截器：性能监控。
 * <p>
 * 默认拦截所有接口，如果要拦截指定接口，可重写 public boolean match() 方法。
 *
 * @author ChenFangjie (2018/12/1 17:03:15)
 * @since 3.0.0
 */
public class PerformanceInterceptor extends ApiInterceptorAdapter {

    private static final Logger log = Log.get();

    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("StopWatch-StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object serviceObj, Object arg) throws Exception {
        long beginTime = System.currentTimeMillis(); // 1、开始时间
        this.startTimeThreadLocal.set(beginTime); // 线程绑定变量（该数据只有当前请求的线程可见）

        log.warn("开始处理请求时间：{}", beginTime);

        return true; // 继续流程
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object serviceObj, Object arg, Object result) throws Exception {
        long endTime = System.currentTimeMillis(); // 2、结束时间
        long beginTime = this.startTimeThreadLocal.get(); // 得到线程绑定的局部变量（开始时间）
        long consumeTime = endTime - beginTime; // 3、消耗的时间

        // 记录到日志文件
        // System.out.println(String.format("请求处理完成：请求 %s 耗时 %d 毫秒。", request.getRequestURI(), consumeTime));
        log.warn("请求处理完成：请求 {} 耗时 {} 毫秒。", request.getRequestURI(), consumeTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object serviceObj, Object arg, Object result, Exception e) throws Exception {
        long endTime = System.currentTimeMillis(); // 2、结束时间
        long beginTime = this.startTimeThreadLocal.get(); // 得到线程绑定的局部变量（开始时间）
        long consumeTime = endTime - beginTime; // 3、消耗的时间

        // if (consumeTime > 500) { // 此处认为处理时间超过500毫秒的请求为慢请求

        // 记录到日志文件
        // System.out.println(String.format("渲染完成：请求 %s 耗时 %d 毫秒。", request.getRequestURI(), consumeTime));
        log.warn("渲染完成：请求 {} 耗时 {} 毫秒。", request.getRequestURI(), consumeTime);

        // }
    }

}
