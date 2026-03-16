package cn.alex.thread;

/**
 * Created by WCY on 2021/3/14
 */
public class MyThread extends Thread {
    public MyThread() {
    }

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("run: " + i);
        }
    }
}
