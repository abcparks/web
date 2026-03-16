package cn.alex.thread.ticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by WCY on 2021/3/14
 */

/*
  卖票案例出现了线程安全问题
  卖出了不存在的票和重复的票
  解决线程安全问题的第三种方案: 使用Lock锁
  Lock实现提供了比使用synchronized方法和语句获得更广泛的锁定操作
  Lock接口方法:
  lock()获取锁和unlock()释放锁
  使用步骤:
  1 在成员位置创建一个ReentrantLock对象
  2 在可能会出现安全问题的代码前调用lock接口的方法lock获取锁
  3 在可能会出现安全问题的代码后调用lock接口的方法unlock释放锁
 */
public class TicketRunnable2 implements Runnable {
    // 定义多线程共享资源
    private static int ticket = 100;

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            // 先判断票是否存在
            if (ticket > 0) {
                // 提高安全问题出现的概率, 让程序睡眠
                try {
                    Thread.sleep(10);
                    // 票存在, 买票 ticket--
                    System.out.println(Thread.currentThread().getName() + "正在卖第" + ticket-- + "张票");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock(); // 放在finally里好处, 无论程序是否会出现异常, 都会把锁释放
                }
            }
        }

    }

}
