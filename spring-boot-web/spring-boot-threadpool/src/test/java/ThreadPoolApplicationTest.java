import cn.alex.ThreadPoolApplication;
import cn.alex.task.CompletableFutureDemo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by WCY on 2021/9/16
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ThreadPoolApplication.class)
public class ThreadPoolApplicationTest {
    @Autowired
    private CompletableFutureDemo completableFutureDemo;

    @Test
    public void CompletableFutureTest() throws InterruptedException, ExecutionException {
        List<String> userNameList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            userNameList.add("Ada" + i);
        }
        long start = System.currentTimeMillis();
        List<String> userInfoResult = completableFutureDemo.batchGetUserInfoByCompletableFuture(userNameList);

        long end = System.currentTimeMillis();
        log.info("CompletableFuture结果" + userInfoResult + "\nCompletableFuture耗时--》" + (end - start) + "ms");
    }
}
