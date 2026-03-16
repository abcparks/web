package cn.alex.conf;

import cn.alex.task.TestTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by WCY on 2021/9/10
 */
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail testQuartz() {
        return JobBuilder.newJob(TestTask.class)
                .withIdentity("testTask")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger testQuartzTrigger() {
        // 5秒执行一次
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(testQuartz())
                .withIdentity("testTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
