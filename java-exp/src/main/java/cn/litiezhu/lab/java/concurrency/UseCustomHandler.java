package cn.litiezhu.lab.java.concurrency;

/**
 * 利用自定义的UncaughtExceptionHandler来全局处理线程中未捕获的异常
 *
 * @author Li Kai
 */
public class UseCustomHandler implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        // 设置自定义的UncaughtExceptionHandler
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("tiezhuHandler"));
        new Thread(new CantCatchDirectly(), "myThread-1").start();
        Thread.sleep(500);
        new Thread(new CantCatchDirectly(), "myThread-2").start();
        Thread.sleep(500);
        new Thread(new CantCatchDirectly(), "myThread-3").start();
        Thread.sleep(500);
        new Thread(new CantCatchDirectly(), "myThread-4").start();
        Thread.sleep(500);
    }
    @Override
    public void run() {
        throw new RuntimeException();
    }
}
