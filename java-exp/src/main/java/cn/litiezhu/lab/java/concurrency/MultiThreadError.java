package cn.litiezhu.lab.java.concurrency;

import java.util.Arrays;

/**
 * 多线程安全问题1：运行结果出错
 * 演示多线程对同一个变量进行自增的操作时实际的结果与预期不符的情况，并找出具体出错的位置。
 *
 * @author Li Kai
 */
public class MultiThreadError implements Runnable {
    int index = 0;
    int errorCnt = 0;
    boolean[] flag = new boolean[20000];

    public static void main(String[] args) throws InterruptedException {
        MultiThreadError runnable = new MultiThreadError();
        Thread thread1 = new Thread(runnable), thread2 = new Thread(runnable);
        // 两个线程同时对index进行10000次自增操作，两个线程结束后，index预期的值应该是20000
        // 但是在实际运行时，index的终值总是比20000小
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("index的终值为：" + runnable.index);
        System.out.println("错误次数总计：" + runnable.errorCnt);

    }

    public MultiThreadError() {
        Arrays.fill(flag, false);
    }
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            int tmp  = ++index;
            // 统计发生错误的次数
            synchronized (this) {
                if (flag[tmp]) {
                    System.out.println("发生错误：" + index);
                    errorCnt++;
                } else {
                    flag[tmp] = true;
                }
            }
        }
    }
}
