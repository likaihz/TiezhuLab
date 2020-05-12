package cn.litiezhu.lab.java.concurrency;

/**
 * 证明Thread对象的BLOCKED, WAITING, TIMED_WAITING状态。
 *
 * @author Li Kai
 */
public class ThreadStates2 implements Runnable {
    public static void main(String[] args) {
        Runnable runnable = new ThreadStates2();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        // 线程1正在定时睡眠，因此应该是TIMED_WAITING状态
        System.out.println("线程1的状态：" + thread1.getState());
        // 线程2正在请求runnable对象的锁，但是还未获取到，因此应该是BLOCKED状态
        System.out.println("线程2的状态：" + thread2.getState());

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 线程1此时阻塞在wait方法，因此应该是WAITING状态
        System.out.println("线程1的状态：" + thread1.getState());


    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
