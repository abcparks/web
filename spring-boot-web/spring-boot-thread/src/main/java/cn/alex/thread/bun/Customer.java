package cn.alex.thread.bun;

import cn.alex.thread.bun.Bun;

/**
 * Created by WCY on 2021/3/14
 */

/*
    消费者类: 是一个线程类
    设置线程任务: 吃包子
    对包子的状态进行判断
    false: 没有包子
        消费者调用wait方法进入等待状态
    true: 有包子
        消费者吃包子
        消费者吃完包子
        修改包子状态为false
        消费者唤醒生产者, 生产包子
 */
public class Customer extends Thread {
    private Bun bun;

    public Customer(Bun bun) {
        this.bun = bun;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (bun) {
                if (!bun.isFlag()) {
                    try {
                        bun.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 消费者吃包子
                System.out.println("消费者正在吃" + bun.getSkin() + bun.getStuffing() + "包子");
                // 修改包子状态
                bun.setFlag(false);
                // 唤醒生产者生产包子
                bun.notify();
                System.out.println("消费者吃了" + bun.getSkin() + bun.getStuffing() + "包子");
                System.out.println("----------------");
            }
        }
    }
}
