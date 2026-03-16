package thread;

import cn.alex.thread.MyRunnable;
import cn.alex.thread.MyRunnable2;
import cn.alex.thread.MyThread;
import cn.alex.thread.ticket.TicketRunnable2;
import org.junit.Test;

/**
 * @Author: Alex
 * @Date: 2021/3/10
 * 线程测试
 */
public class ThreadTest {
    // 线程的创建
    @Test
    public void start() {
        // 创建Thread类子类对象
        MyThread myThread = new MyThread();
        // 调用Thread类中start方法, 开启新的线程, 执行run方法
        myThread.start(); // 执行start()启动线程, 开辟新的栈空间
        //myThread.run(); // 不走线程

        for (int i = 0; i < 20; i++) {
            System.out.println("main: " + i);
        }

        // 创建线程
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(1);
            }
        });
        thread.start();
    }

    /*
      获取线程的名称
      1 使用Thread类中的getName方法
      2 可以获取当前正在执行的线程, 使用线程中的方法getName方法获取线程的名称
      static Thread currentThread()返回当前正在执行的线程对象引用
     */
    @Test
    public void t2() {
        MyThread myThread = new MyThread();
        System.out.println(myThread.getName()); // 获取myThread线程的名称
        // 链式编程 Thread.currentThread().getName()
        System.out.println(Thread.currentThread().getName()); // 获取当前线程的名称
    }

    /*
      设置线程的名称
      1 使用Thread类setName方法
      2 创建一个带参数的构造方法, 参数传递线程名称, 调用父类的带参构造方法
     */
    @Test
    public void t3() {
        MyThread myThread = new MyThread();
        System.out.println(myThread.getName()); // 获取myThread线程的名称
        myThread.setName("线程名");
        System.out.println(myThread.getName()); // 获取myThread线程的名称

        MyThread myThread2 = new MyThread("线程名2");
        System.out.println(myThread2.getName()); // 获取myThread线程的名称
    }

    // 线程暂停
    @Test
    public void t4() {
        for (int i = 0; i < 60; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 创建线程第二种方式
    @Test
    public void t5() {
        Runnable runnable = new MyRunnable();
        //Thread thread = new Thread(runnable);
        Thread thread = new Thread(new MyRunnable2());
        thread.start();
    }

    /*
      匿名内部类方式实现线程的创建
      匿名: 没有名字
      内部类: 写在其他类内部的类
      匿名内部类的作用: 简化代码
      把字类继承父类重写父类的方法, 创建子类对象合一步完成
      把实现类实现父类接口重写接口中方法, 创建实现类对象合一步完成
     */
    @Test
    public void t6() {
        // 线程的父类是Thread
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("Hello World1!");
                }
            }
        };

        // 线程的接口是Runnable
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Hello World2!");
                }
            }
        });

        // 简化接口方式
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Hello World3!");
            }
        });

        thread.start();
        thread2.start();
        thread3.start();
    }

    /*
      买票案例
      junit单元测试不支持多线程
      当在junit单元测试中开启线程, 在主线程运行结束后就关闭了, 而不会等到子线程运行结束,
      而main函数就不存在这个问题...
      解决方案, 如果想用junit进行多线程测试, 可以先睡眠主线程
     */
    @Test
    public void t7() {
        try {
            Thread.sleep(1000 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
        Thread synchronized join()
        wait(): 主线程调用
        isActive(): 新线程调用
        1 为什么wait()是主线程调用
        首先要注意join()上面有synchronized关键字, 所以进去执行的线程需要获取锁才能进入
        在main线程中调用的是thread.join(), 所以main线程获取了锁, 同步方法获取的对象锁是Thread这个对象thread
        所以是main线程获取了Thread的对象锁并且调用了wait方法, 所以是main线程等待
        2 为什么isActive()是新线程调用
        这里调用isActive()是Thread类的方法, 判断的是Thread类对象代表的线程状态, 所以这里判断当前实例对象Thread的对象状态

        注意: 调用对象wait()和notify()方法前, 必须要获得对象锁
     */
    public static void main(String[] args) throws InterruptedException {
        // 创建Runnable接口实现类对象
        //Runnable runnable = new TicketRunnable();
        Runnable runnable = new TicketRunnable2();
        // 创建Thread类对象, 构造方法中传递Runnable接口的实现类对象
        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        // 调用start方法开启线程
        thread.start();

        /*new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.interrupt(); // 中断线程, 需要和一起使用!Thread.currentThread().isInterrupted()
        }).start();*/

        //thread.join(); // 使得thread线程执行完毕之前, 主线程一直在等待
        thread2.start();
        thread3.start();
        //System.out.println(11111);
    }

    /*
        wait(), notify(), notifyAll() 必须在synchronized代码块中
        wait(): 线程在获取对象锁之后, 主动释放对象锁, 同时本线程休眠, 直到其它线程调用对象的notify()唤醒该线程
        notify(): 只是让随机一个线程从wait中恢复过来, 继续执行后面的代码, notify()调用后并不是马上就释放锁,
        而是在相应synchronized代码块执行结束, 自动释放锁, JVM会在wait对象锁的线程中随机选择一个线程
        notifyAll(): 让所有的线程从wait中恢复, 继续执行后面的代码
     */
}
