package cn.litiezhu.lab.java.concurrency.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 利用ThreadMXBean检测程序在运行时的死锁。
 *
 * @author Li Kai
 */
public class ThreadMXBeanDetection implements Runnable {
    static Object lock1 = new Object();
    static Object lock2 = new Object();


    int flag = 1;
    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] flag = " + flag);

        if (flag == 1) {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + "获取了锁1");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "获取了锁2");
                }

            }
        }
        if (flag == 0) {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "获取了锁2");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "获取了锁1");
                }

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadMXBeanDetection runnable1 = new ThreadMXBeanDetection();
        ThreadMXBeanDetection runnable2 = new ThreadMXBeanDetection();

        runnable2.flag = 0;

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);

        t1.start();
        t2.start();

        Thread.sleep(1000);

        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();

        long[] deadlockedThreads = mxBean.findDeadlockedThreads();

        if (deadlockedThreads != null && deadlockedThreads.length > 0) {
            for (long tid : deadlockedThreads) {
                ThreadInfo threadInfo = mxBean.getThreadInfo(tid);
                System.out.println("发现死锁：" + threadInfo.getThreadName());

            }
        }


    }
}
