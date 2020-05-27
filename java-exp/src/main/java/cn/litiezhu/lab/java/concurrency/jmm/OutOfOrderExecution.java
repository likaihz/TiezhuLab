package cn.litiezhu.lab.java.concurrency.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 演示重排序的现象。
 * 由于重排序不是一定会发生的，因此需要多次重复执行，直到达到某个条件才停止。
 *
 * @author Li Kai
 */
public class OutOfOrderExecution {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            // 用于将两个线程同时放行
            CountDownLatch latch = new CountDownLatch(1);

            Thread thread1 = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });
            Thread thread2 = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });

            // 两个线程的开始顺序换一下，结果就可能不同
            thread1.start();
            thread2.start();

            latch.countDown();
            thread1.join();
            thread2.join();

            System.out.println("x = " + x + ", y = " + y);
            if (x == 1 && y == 1) {
                System.out.println("发生了交替执行：" + i);
            } else if (x == 0 && y == 0) {
                System.out.println("发生了重排序，执行了" + i + "次");
                break;
            }
        }

    }
}
