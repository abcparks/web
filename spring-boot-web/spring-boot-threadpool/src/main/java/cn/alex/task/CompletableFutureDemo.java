package cn.alex.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Created by WCY on 2021/9/16
 */
@Component
@Slf4j
public class CompletableFutureDemo {
    @Autowired
    private AsyncTask asyncTask;

    public List<String> batchGetUserInfoByCompletableFuture(List<String> userNameList) throws InterruptedException, ExecutionException {
        List<CompletableFuture<String>> userInfoFutures = userNameList.stream()
                .map(asyncTask::getUserInfoByCompletableFuture).collect(Collectors.toList());
        return userInfoFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
}
