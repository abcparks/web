import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by WCY on 2021/9/17
 */
public class SynchronizeTest {

    @Test
    public void lockHeader() {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

    @Test
    public void reentrantLock() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        // 阻塞和唤醒
        condition.await();
        condition.signal();
        condition.signalAll();

        lock.lock();
        lock.unlock();
    }
}
