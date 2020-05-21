package cn.litiezhu.lab.java.concurrency;

/**
 * 1. 在子线程中不加try/catch抛出异常，都带线程名字
 * 2. 在主线程中加try/catch，期望捕获到第一个线程的异常，线程234不应该运行，希望看到打印出caught exception
 * 3. 执行时发现，根本没有caught exception，线程234依然运行并且抛出异常
 *
 * 说明子线程的异常不能用传统方法在主线程中捕获。
 * @author Li Kai
 */
public class CantCatchDirectly implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        try {
            new Thread(new CantCatchDirectly(), "myThread-1").start();
            Thread.sleep(500);
            new Thread(new CantCatchDirectly(), "myThread-2").start();
            Thread.sleep(500);
            new Thread(new CantCatchDirectly(), "myThread-3").start();
            Thread.sleep(500);
            new Thread(new CantCatchDirectly(), "myThread-4").start();
            Thread.sleep(500);
        } catch (RuntimeException e) {
            System.out.println("Caught exception.");
        }
    }
    @Override
    public void run() {
        throw new RuntimeException();
    }

}
