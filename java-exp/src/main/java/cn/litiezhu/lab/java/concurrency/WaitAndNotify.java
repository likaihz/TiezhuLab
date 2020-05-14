package cn.litiezhu.lab.java.concurrency;

/**
 * 探索wait和notify方法的基本用法
 * 1. 研究代码的执行顺序
 * 2. 证明wait方法会释放锁
 *
 * @author Li Kai
 */
public class WaitAndNotify {
    public static Object object = new Object();

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程1开始执行了。");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1被唤醒并获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程2开始执行了。");
                object.notify();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程2结束。");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2();

        thread1.start();
        Thread.sleep(200);
        thread2.start();

    }

}
