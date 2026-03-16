package cn.alex.thread.bun;

import cn.alex.thread.bun.Bun;

/**
 * Created by WCY on 2021/3/14
 */

/*
    生产者(包子铺): 是一个线程类
    设置线程任务: 生成包子
    对包子状态进行判断
    true: 有包子
        生产者(包子铺)调用wait方法进入等待状态
    false: 没有包子
        生产者(包子铺)生成包子
        增加趣味性: 交替生成两种包子
        有两种状态(i%2==0)
        生产者(包子铺)生产好包子
        修改包子的状态为true
        唤醒消费者线程, 让消费者消费包子
    注意:
    生产者线程和消费者线程关系 通信(互斥)
    必须同时同步技术保证两个线程只能有一个在执行
    锁对象必须保证唯一, 可以是使用包子对象作为锁对象
    生产者和消费者类需要把包子对象作为参数传递进来
    1 需要在成员位置创建包子变量
    2 使用带参数构造方法为包子变量赋值
 */
public class Producer extends Thread {
    private Bun bun;

    public Producer(Bun bun) {
        this.bun = bun;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            synchronized (bun) {
                if (bun.isFlag()) { // 对包子状态判断
                    try {
                        bun.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 被唤醒后生产包子
                if (count % 2 == 0) { // 生产薄皮三鲜馅包子
                    bun.setSkin("薄皮");
                    bun.setStuffing("三鲜馅");
                } else { // 生产冰皮牛肉大葱馅包子
                    bun.setSkin("冰皮");
                    bun.setStuffing("牛肉大葱馅");
                }
                count++;
                System.out.println("包子铺正在生产: " + bun.getSkin() + bun.getStuffing() + "包子");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 生产者生产好包子, 修改包子状态
                bun.setFlag(true);
                // 唤醒消费者吃包子
                bun.notify();
                System.out.println("包子铺已经生产好了: " + bun.getSkin() + bun.getStuffing() + "包子");
            }
        }
    }
}
