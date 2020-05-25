package cn.litiezhu.lab.java.concurrency;

/**
 * 多线程安全问题2：活跃性问题（以死锁为例）
 * 必然发生死锁的场景。
 *
 * @author Li Kai
 */
public class MultiThreadLiveness implements Runnable {
    int flag;
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MultiThreadLiveness(1));
        Thread thread2 = new Thread(new MultiThreadLiveness(0));

        thread1.start();
        thread2.start();

    }

    public MultiThreadLiveness(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] flag = " + flag);

        if (flag == 1) {
            synchronized (lock1) {
                System.out.println("[" + Thread.currentThread().getName() + "] 获得lock1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println("[" + Thread.currentThread().getName() + "] 获得lock2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (flag == 0) {
            synchronized (lock2) {
                System.out.println("[" + Thread.currentThread().getName() + "] 获得lock2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.println("[" + Thread.currentThread().getName() + "] 获得lock1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
