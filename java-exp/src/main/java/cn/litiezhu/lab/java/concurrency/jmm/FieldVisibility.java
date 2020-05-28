package cn.litiezhu.lab.java.concurrency.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 演示可见性带来的问题
 *
 * @author Li Kai
 * @see FieldVisibilityVolatile
 */
public class FieldVisibility {
    int a = 1;
    int b = 2;

    public static void main(String[] args) {
        while (true) {
            CountDownLatch latch = new CountDownLatch(1);
            FieldVisibility instance = new FieldVisibility();

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
        // 这个赋值的顺序的隐含含义：只要b的值为3，那么a也应该为3
        this.a = 3;
        this.b = this.a;
    }

    public void print() {
        // 经过足够多次的运行，能够得到少量的b = 3, a = 1的结果
        // 这个结果就是可见性导致的，读线程只看到了b的最新的值，而没有看到a的最新的值
        System.out.println("b = " + b + ", a = " + a);
    }

}
