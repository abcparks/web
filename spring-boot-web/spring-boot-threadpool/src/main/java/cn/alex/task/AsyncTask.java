package cn.alex.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * 任务异步处理
 * Created by WCY on 2021/9/16
 */
@Component
@Slf4j
public class AsyncTask {

    @Async("primaryAsyncTaskExecutor")
    public CompletableFuture<String> getUserInfoByCompletableFuture(String userName) {
        String userInfo = "";
        try {
            Thread.sleep(2 * 1000);
            userInfo = userName + "的基本信息!";
            log.info("线程: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(userInfo);
    }
}
