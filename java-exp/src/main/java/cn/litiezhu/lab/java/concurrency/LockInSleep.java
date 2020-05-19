package cn.litiezhu.lab.java.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 验证线程在sleep的过程中不会释放lock锁。
 *
 * @author Li Kai
 */
public class LockInSleep implements Runnable {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInSleep runnable = new LockInSleep();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    @Override
    public void run() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "获取到锁。");
        // 在使用lock锁时，一定要记得处理异常的时候释放锁
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁。");
            lock.unlock();
        }
    }
}
