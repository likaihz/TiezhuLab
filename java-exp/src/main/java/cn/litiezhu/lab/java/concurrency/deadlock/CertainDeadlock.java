package cn.litiezhu.lab.java.concurrency.deadlock;

/**
 * 必然发生死锁的情况。
 *
 * @author Li Kai
 */
public class CertainDeadlock implements Runnable {
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

    public static void main(String[] args) {
        CertainDeadlock runnable1 = new CertainDeadlock();
        CertainDeadlock runnable2 = new CertainDeadlock();

        runnable2.flag = 0;

        new Thread(runnable1).start();
        new Thread(runnable2).start();

    }
}
