package cn.litiezhu.lab.java.concurrency;

/**
 * 两个线程交替打印0～100的奇偶数，用synchronized关键字实现。
 *
 * @author Li Kai
 */
public class OddAndEvenSync {
    private static int count = 0;

    private static Object lock = new Object();
    // 新建两个线程
    // 一个只处理偶数，另一个只处理奇数（用位运算）
    // 用synchronized来通信

    public static void main(String[] args) {
        // 处理偶数的线程
        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    // 用位运算来判断奇偶性
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + count++);

                    } else {
                        System.out.println(Thread.currentThread().getName() + "失败");
                    }
                }
            }
        }, "evenThread").start();

        // 处理奇数的线程
        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + ": " + count++);

                    } else {
                        System.out.println(Thread.currentThread().getName() + "失败");
                    }
                }
            }
        }, "oddThread").start();

    }
}
