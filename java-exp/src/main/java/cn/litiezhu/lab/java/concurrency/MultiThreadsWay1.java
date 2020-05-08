package cn.litiezhu.lab.java.concurrency;

/**
 * 通过实现Runnable接口的方式创建子线程并启动。
 *
 * @author Li Kai
 */
public class MultiThreadsWay1 implements Runnable {
    @Override
    public void run() {
        // 打印子线程的name
        System.out.println(Thread.currentThread().getName()+" is running.");
    }

    public static void main(String[] args) {
        // 打印主线程（父线程）的name
        System.out.println(Thread.currentThread().getName() + " is running.");

        // 创建线程对象
        Thread thread = new Thread(new MultiThreadsWay1());

        // 启动子线程
        thread.start();

    }
}
