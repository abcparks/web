package cn.alex.thread.ticket;

/**
 * Created by WCY on 2021/3/14
 */

/*
  卖票案例出现了线程安全问题
  卖出了不存在的票和重复的票
  解决线程安全问题的一种方案: 使用同步代码块
  格式:
  synchronized() {
  可能会出现线程安全问题的代码(访问共享数据的代码)
  }
  注意:
  1 通过代码块的对象, 可以使用任意对象
  2 但是必须保证多个线程使用的锁对象是同一个
  3 锁对象作用: 把同步代码块锁住, 只让一个线程在同步代码块中执行
  解决线程安全问题的第二种方案: 使用同步方法
  使用步骤:
  1 把访问了恭喜数据的代码抽出来, 放到一个方法中
  2 在方法上添加synchronized修饰符
  格式: 定义方法的格式
  修饰符 synchronized 返回值类型 方法名(参数列表) {
  可能会出现线程安全问题的代码(访问共享数据的代码)
  }
 */
public class TicketRunnable implements Runnable {
    // 定义多线程共享资源
    private static int ticket = 100;

    // 创建一个锁对象
    Object obj = new Object();

    @Override
    public void run() {
        /*while (true) {
            //synchronized (this) { // 同步代码块
            synchronized (obj) { // 同步代码块
                // 先判断票是否存在
                if (ticket > 0) {
                    // 提高安全问题出现的概率, 让程序睡眠
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 票存在, 买票 ticket--
                    System.out.println(Thread.currentThread().getName() + "正在卖第" + ticket-- + "张票");
                }
            }
        }*/
        while (true) {
        //while (!Thread.currentThread().isInterrupted()) { // 中断线程, 与thread.interrupt()一起使用
            sellTicket();
        }
    }

    // 定义一个同步方法 锁对象是this, 即调用run方法的对象
    /*public synchronized void sellTicket() {
        if (ticket > 0) {
            // 提高安全问题出现的概率, 让程序睡眠
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 票存在, 买票 ticket--
            System.out.println(Thread.currentThread().getName() + "正在卖第" + ticket-- + "张票");
        }
    }*/

    /*
        定义一个静态同步方法
        锁对象是谁?
        不能是this
        this是创建对象之后产生的, 静态方法优先于对象
        静态方法的锁对象是本类的class属性-->class文件对象 TicketRunnable.class
     */
    public static synchronized void sellTicket() {
        if (ticket > 0) {
            // 提高安全问题出现的概率, 让程序睡眠
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 票存在, 买票 ticket--
            System.out.println(Thread.currentThread().getName() + "正在卖第" + ticket-- + "张票");
        }
    }
}
