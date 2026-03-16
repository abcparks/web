package cn.alex.config;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by WCY on 2021/8/4
 */
@Configuration
public class ElasticJobConfig {

    @Value("${elasticjob.zk-server-list}")
    private String zkServerList;

    @Value("${elasticjob.zk-namespace}")
    private String zkNameSpace;

    @Autowired
    private DataSource dataSource;

    // 配置zk
    @Bean("zookeeperConfiguration")
    public ZookeeperConfiguration zookeeperConfiguration() {
        return new ZookeeperConfiguration(zkServerList, zkNameSpace);
    }

    // 初始化 任务注册中心
    @Bean(initMethod = "init")
    public CoordinatorRegistryCenter elasticjobRegistryCenter(@Qualifier("zookeeperConfiguration") ZookeeperConfiguration config) {
        return new ZookeeperRegistryCenter(config);
    }

    // 将job任务轨迹持久化到数据库
    @Bean("jobEventConfiguration")
    public JobEventConfiguration jobEventConfiguration() {
        return new JobEventRdbConfiguration(dataSource);
    }

    // elasticjob 任务监听器
    @Bean
    public ElasticJobListener elasticJobListener(@Value("${elasticjob.startedTimeoutMilliseconds}") long startedTimeoutMilliseconds,
                                                 @Value("${elasticjob.completedTimeoutMilliseconds}") long completedTimeoutMilliseconds) {
        return new AbstractDistributeOnceElasticJobListener(startedTimeoutMilliseconds, completedTimeoutMilliseconds) {
            @Override
            public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts) {
                System.out.println("---doBeforeJobExecutedAtLastStarted---");
                System.out.println("---" + shardingContexts + "---");
            }

            @Override
            public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts) {
                System.out.println("---doAfterJobExecutedAtLastCompleted---");
                System.out.println("---" + shardingContexts + "---");
            }
        };
    }
}
