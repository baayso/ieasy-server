package com.baayso.springboot.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 异步任务配置。
 *
 * @author ChenFangjie (2022/3/6 17:57)
 * @since 4.0.0
 */
@EnableAsync
@Configuration
public class AsyncTaskConfig implements AsyncConfigurer {

    public static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    @Override
    public Executor getAsyncExecutor() {
        final int poolSize = AVAILABLE_PROCESSORS + 1;
        final int queueSize = AVAILABLE_PROCESSORS * 2;

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(poolSize);
        executor.setMaxPoolSize(poolSize);
        executor.setQueueCapacity(queueSize);
        executor.setThreadNamePrefix("AsyncTask-");
        // executor.setRejectedExecutionHandler();
        executor.initialize();

        return executor;
    }

}
