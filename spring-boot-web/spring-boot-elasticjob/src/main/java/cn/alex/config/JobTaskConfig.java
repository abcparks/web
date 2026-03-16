package cn.alex.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Created by WCY on 2021/8/4
 */
@Configuration
public class JobTaskConfig {
    @Resource
    private ElasticJobListener elasticJobListener;

    @Autowired
    private CoordinatorRegistryCenter registryCenter;

    @Resource
    private JobEventConfiguration jobEventConfiguration;

    @Value("${elasticjob.dump-port}")
    private Integer dumpPort;

    @Bean(initMethod = "init") // 将任务交给定时任务线程池调度
    public JobScheduler simpleJobScheduler(@Qualifier("mySimpleJob") SimpleJob job) {
        final String jobName = "simpleJob";
        // 每10s执行一次
        final String cron = "*/10 * * * * ?";

        // 分片总数, 分配策略
        int shardingTotalCount = 2;
        final String shardingItemParameters = "0=wcy,1=wxx";

        LiteJobConfiguration jobConfiguration = this.createJobConfiguration(job.getClass(), cron, shardingTotalCount, shardingItemParameters, "mySimpleJob");

        // 1 具体任务对象 2 zk注册中心 3 任务配置参数 4 任务轨迹持久化到数据库配置 5 为当前任务配置具体监听器
        return new SpringJobScheduler(job, registryCenter, jobConfiguration, jobEventConfiguration, elasticJobListener);
    }

    /**
     * @param jobClass
     * @param cron
     * @param shardingTotalCount
     * @param shardingItemParameters
     * @param jobParameters
     * @return
     */
    private LiteJobConfiguration createJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                        final String cron,
                                                        final int shardingTotalCount,
                                                        final String shardingItemParameters,
                                                        final String jobParameters) {
        // JobCoreConfigurationBuilder
        JobCoreConfiguration.Builder jobCoreConfigurationBuilder = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount);
        // 设置shardingItemParameters
        jobCoreConfigurationBuilder.failover(true);// 故障转移
        jobCoreConfigurationBuilder.jobParameter(jobParameters); // 还可以针对job设置条件参数
        if (StringUtils.isNoneEmpty(shardingItemParameters)) {
            jobCoreConfigurationBuilder.shardingItemParameters(shardingItemParameters);
        }
        JobCoreConfiguration jobCoreConfiguration = jobCoreConfigurationBuilder.build();
        // 创建SimpleJobConfiguration
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, jobClass.getCanonicalName());
        // 创建LiteJobConfiguration
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(true)
                //.monitorPort(dumpPort) // 设置dump端口
                //.disabled(true) // 先禁止启动, 部署结束后统一启动
                .build();
        return liteJobConfiguration;
    }
}
