package cn.litiezhu.lab.java.concurrency;

/**
 * 线程在阻塞时被中断，有一些阻塞的方法会在被中断时抛出异常（如sleep方法）。
 *
 * @author Li Kai
 */
public class InterruptWithSleep implements Runnable {
    @Override
    public void run() {
        int num = 0;
        System.out.println(Thread.currentThread().getName() + " is sleeping.");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is terminated.");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new InterruptWithSleep());
        thread.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
