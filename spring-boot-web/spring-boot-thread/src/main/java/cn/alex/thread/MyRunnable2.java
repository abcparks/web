package cn.alex.thread;

/**
 * Created by WCY on 2021/3/14
 */

/*
  创建线程第二种方式: 实现Runnable接口
  Runnable接口应该由那些打算通过某一线程执行其实例的类来实现, 类必须定义一个称为run的无参方法
  Thread(Runnable runnable) 分配新的Thread对象
  Thread(Runnable runnable, String name) 分配新的Thread对象
  实现步骤
  1 创建一个Runnable接口的实现类
  2 在实现类中重写run方法, 设置线程任务
  3 创建一个Runnable实现类对象
  4 创建Thread类对象, 构造方法中传递Runnable实现类对象
  5 调用Thread类中start方法, 开启新的线程执行run方法
  实现Runnable的接口的好处
  1 避免了单继承的局限性, 类继承Thread类就不能继承其他的类
  实现了Runnable接口还可以继承其他类, 实现其他接口
  2 增强程序的扩展性, 降低程序的耦合性(解耦), 实现Runnable接口的方式, 把设置线程任务和开启新线程进行了分离(解耦)
  实现类中重写run方法, 用来设置线程任务
  创建Thread对象, 调用start方法用来开启线程
 */
public class MyRunnable2 implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello World!");
    }
}
