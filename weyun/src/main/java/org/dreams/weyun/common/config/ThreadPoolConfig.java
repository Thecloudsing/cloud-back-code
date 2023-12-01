package org.dreams.weyun.common.config;

import org.dreams.weyun.common.factory.CustomizationThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/19
 */
@Configuration
public class ThreadPoolConfig implements AsyncConfigurer {
    public static final String CORE_EXECUTOR = "core-executor";
    public static final String WS_EXECUTOR = "websocket-executor";
    @Bean(name = CORE_EXECUTOR)
    public ThreadPoolTaskExecutor coreExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(20); // 最大线程数
        executor.setCorePoolSize(10); // 核心线程数
        executor.setQueueCapacity(200); // 队列容量
        executor.setKeepAliveSeconds(60); // 线程空闲时间
        executor.setThreadNamePrefix("sore-executor-"); // 线程前缀名
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//满了调用线程执行，认为重要任务
        executor.setThreadFactory(new CustomizationThreadFactory(executor));
        executor.initialize();
        return executor;
    }
    @Bean(name = WS_EXECUTOR)
    public ThreadPoolTaskExecutor wsExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(20); // 最大线程数
        executor.setCorePoolSize(10); // 核心线程数
        executor.setQueueCapacity(1000); // 队列容量
        executor.setKeepAliveSeconds(60); // 线程空闲时间
        executor.setThreadNamePrefix("websocket-executor-"); // 线程前缀名
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());//满了直接丢弃，默认为不重要消息推送
        executor.setThreadFactory(new CustomizationThreadFactory(executor));
        executor.initialize();
        return executor;
    }
    @Override
    public Executor getAsyncExecutor() {
        return coreExecutor();
    }
}
