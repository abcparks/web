package cn.alex.jobtask;

import cn.alex.service.SimpleService;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by WCY on 2021/8/4
 */
@Component("mySimpleJob")
@Slf4j
public class MySimpleJob implements SimpleJob {
    //@Override
    //public void execute(ShardingContext shardingContext) {
    //    String jobParameter = shardingContext.getJobParameter();
    //    System.out.println("jobParameter = " + jobParameter);
    //    // mySimpleJob 看是否有自定义参数
    //    log.info("任务开始了, shardingContext = {}", shardingContext);
    //
    //    // Feign调度service执行具体的业务操作
    //}

    @Autowired
    private SimpleService simpleService;

    @Override
    public void execute(ShardingContext shardingContext) {
        int shardingTotalCount = shardingContext.getShardingTotalCount();
        String shardingParameter = shardingContext.getShardingParameter();
        log.info("任务开始了, shardingTotalCount = {}, shardingParameter = {}", shardingTotalCount, shardingParameter);

        // 更新用户年龄
        simpleService.updateUser(shardingParameter);
    }
}
