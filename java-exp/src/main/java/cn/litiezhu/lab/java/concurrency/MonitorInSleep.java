package cn.litiezhu.lab.java.concurrency;

/**
 * 验证线程在sleep的过程中是不释放synchronized锁的，会一直等到sleep时间结束后，
 * synchronized代码块结束时才会释放锁。
 *
 * @author Li Kai
 */
public class MonitorInSleep implements Runnable {
    public static void main(String[] args) {
        MonitorInSleep runnable = new MonitorInSleep();
        // 两个线程都要获取到runnable的monitor锁。在一个线程sleep时，另一个线程无法获取到锁
        new Thread(runnable).start();
        new Thread(runnable).start();

    }
    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        System.out.println(Thread.currentThread().getName() + "获取到了monitor锁。");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "退出了同步代码块。");

    }
}
