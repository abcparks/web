package cn.alex.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 多个线程池自定义配置
 * Created by WCY on 2021/9/16
 */
@EnableAsync
@Configuration
public class AsyncTaskPoolConfig {

    private final PrimaryAsyncContent primaryAsyncContent;
    private final SecondaryAsyncContent secondaryAsyncContent;

    @Autowired(required = false)
    public AsyncTaskPoolConfig(PrimaryAsyncContent primaryAsyncContent, SecondaryAsyncContent secondaryAsyncContent) {
        this.primaryAsyncContent = primaryAsyncContent;
        this.secondaryAsyncContent = secondaryAsyncContent;
    }

    @Bean(name = "primaryAsyncTaskExecutor")
    public Executor getPrimaryAsyncTaskExecutor() {
        return initExecutor(primaryAsyncContent, "primaryAsyncTaskExecutor-");
    }

    @Bean(name = "secondaryAsyncTaskExecutor")
    public Executor getSecondaryAsyncTaskExecutor() {
        return initExecutor(secondaryAsyncContent, "secondaryAsyncTaskExecutor-");
    }

    private ThreadPoolTaskExecutor initExecutor(AsyncContent asyncContent, String prefix) {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(asyncContent.getCorePoolSize());
        threadPoolTaskExecutor.setMaxPoolSize(asyncContent.getMaxPoolSize());
        threadPoolTaskExecutor.setQueueCapacity(asyncContent.getQueueCapacity());
        threadPoolTaskExecutor.setKeepAliveSeconds(asyncContent.getKeepAliveSeconds());
        threadPoolTaskExecutor.setThreadNamePrefix(prefix);

        /*
            线程池对拒绝任务(无线程可用)的处理策略
            AbortPolicy 丢弃任务, 抛运行时异常
            DiscardPolicy 忽视, 什么都不会发生
            CallerRunsPolicy 执行任务
            DiscardOldestPolicy 从队列中踢出最先进入队列(最后一个执行)的任务
         */
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolTaskExecutor;
    }
}
