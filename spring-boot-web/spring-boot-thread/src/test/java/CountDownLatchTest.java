import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by WCY on 2021/9/17
 */
public class CountDownLatchTest {
    static int num = 0;

    @Test
    public void count() throws InterruptedException {
        int len = 100;
        CountDownLatch countDownLatch = new CountDownLatch(len);
        Thread[] threads = new Thread[len];
        for (int i = 0; i < len; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 500000; j++) {
                    synchronized (CountDownLatchTest.class) {
                        num++;
                    }
                }
                countDownLatch.countDown();
            });
        }
        for (int i = 0; i < len; i++) {
            threads[i].start();
        }
        countDownLatch.await();
        System.out.println(num);
    }
}
