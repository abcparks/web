import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by WCY on 2021/9/17
 */
public class AtomicTest {

    @Test
    public void incrementAndGet() throws InterruptedException {
        int len = 100;
        Thread[] threads = new Thread[len];

        CountDownLatch countDownLatch = new CountDownLatch(len);

        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < len; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 500000; j++) {
                    atomicInteger.incrementAndGet();
                }
                countDownLatch.countDown();
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        // 阻塞
        countDownLatch.await();
        System.out.println(atomicInteger.get());
    }

    @Test
    public void compareAndSet() {
        // 戳
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(0, 0);
        for (int i = 0; i < 100; i++) {
            Integer reference = atomicStampedReference.getReference();
            int stamp = atomicStampedReference.getStamp();
            atomicStampedReference.compareAndSet(reference, ++reference, stamp, stamp + i);
        }
        System.out.println(atomicStampedReference.getReference());
    }

}
