package cn.litiezhu.lab.java.concurrency.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 演示volatile给变量带来的可见性。
 *
 * @author Li Kai
 * @see FieldVisibility
 */
public class FieldVisibilityVolatile {
    // 加上volatile关键字后可以保证可见性
    // 不会出现b = 3, a = 1的结果
    int a = 1;
    volatile int b = 2;

    public static void main(String[] args) {
        while (true) {
            CountDownLatch latch = new CountDownLatch(1);
            FieldVisibilityVolatile instance = new FieldVisibilityVolatile();

            Thread thread1 = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance.change();
            });
            thread1.start();

            Thread thread2 = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance.print();
            });
            thread2.start();
            latch.countDown();
        }

    }

    public void change() {
        this.a = 3;
        this.b = this.a;
    }

    public void print() {
        System.out.println("b = " + this.b + ", a = " + this.a);
    }
}
