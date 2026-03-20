package com.ootd.fitme.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.support.CompositeTaskDecorator;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        return eventExecutor();
    }

    @Bean(name = "eventTaskExecutor")
    public ThreadPoolTaskExecutor eventExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(60);

        executor.setThreadNamePrefix("eventTaskExecutor-");

        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);

        executor.setTaskDecorator(
                new CompositeTaskDecorator(List.of(
                        new SecurityContextTaskDecorator(),
                        new LoggingTaskDecorator()

                ))
        );

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy()); // 실패시 오래된것부터 버림

        executor.initialize();

        log.info("eventExecutor Initialized: corePoolSize={}, maxPoolSize={}, queueCapacity={}, keepAliveSeconds={}",
                executor.getCorePoolSize(),
                executor.getMaxPoolSize(),
                executor.getQueueCapacity(),
                executor.getKeepAliveSeconds()
        );

        return executor;
    }
}
