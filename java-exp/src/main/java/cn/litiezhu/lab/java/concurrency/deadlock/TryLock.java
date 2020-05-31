package cn.litiezhu.lab.java.concurrency.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用tryLock来避免死锁。
 *
 * @author Li Kai
 */
public class TryLock implements Runnable {

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();


    int flag = 1;
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag == 1) {
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        System.out.println(Thread.currentThread().getName()+"成功获取了锁1。");
                        Thread.sleep(new Random().nextInt(1000));
                        if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                            System.out.println(Thread.currentThread().getName() + "成功获取了两把锁。");
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        } else {
                            System.out.println(Thread.currentThread().getName()+"获取锁2失败，重试。。。");
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName()+"获取锁1失败，重试。。。");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (flag == 0) {
                try {
                    if (lock2.tryLock(3000, TimeUnit.MILLISECONDS)) {
                        System.out.println(Thread.currentThread().getName()+"成功获取了锁2。");
                        Thread.sleep(new Random().nextInt(1000));
                        if (lock1.tryLock(3000, TimeUnit.MILLISECONDS)) {
                            System.out.println(Thread.currentThread().getName() + "成功获取了两把锁。");
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        } else {
                            System.out.println(Thread.currentThread().getName()+"获取锁1失败，重试。。。");
                            lock2.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName()+"获取锁2失败，重试。。。");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TryLock r1 = new TryLock();
        TryLock r2 = new TryLock();

        r1.flag = 1;
        r2.flag = 0;

        new Thread(r1).start();
        new Thread(r2).start();

    }
}
