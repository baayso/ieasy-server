package com.baayso.springboot.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * 自定义线程池。
 *
 * @author ChenFangjie (2021/2/27 12:32)
 * @since 4.0.0
 */
public class ThreadPool {

    public static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static ExecutorService newThreadPool(String poolName) {
        final int poolSize = AVAILABLE_PROCESSORS + 1;
        final int queueSize = AVAILABLE_PROCESSORS;

        return new ThreadPoolExecutor(
                poolSize,
                poolSize,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(queueSize),
                new DefaultThreadFactory(poolName),
                new ThreadPoolExecutor.AbortPolicy());
    }

}
