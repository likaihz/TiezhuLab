package cn.litiezhu.lab.java.concurrency;

/**
 * 两个线程交替打印0～100的奇偶数，用wait/notify实现
 *
 * @author Li Kai
 */
public class OddAndEvenWait {
    // 拿到锁就打印，不用判断奇偶
    // 打印完后唤醒其他线程，然后自己休眠

    private static int count = 0;

    private static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new TurningRunner(), "evenThread").start();
        // 为了防止线程顺序错乱，可以休眠一小段时间
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new Thread(new TurningRunner(), "oddThread").start();
    }

    public static class TurningRunner implements Runnable {
        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ": " + count++);
                    lock.notify();
                    if(count <= 100) {
                        try {
                            // 如果任务还没结束，就让出锁，自己休眠
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
