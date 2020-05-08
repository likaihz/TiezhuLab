package cn.litiezhu.lab.java.concurrency;

/**
 * 通过继承Thread类的方式创建子线程并启动。
 * 不推荐这种方法，因为会导致与Thread耦合，无法继承其他类，也无法使用线程池。
 *
 * @author Li Kai
 */
public class MultiThreadWay2 extends Thread {
    @Override
    public void run() {
        // 打印子线程的name
        System.out.println(Thread.currentThread().getName()+" is running.");
    }

    public static void main(String[] args) {
        // 打印主线程（父线程）的name
        System.out.println(Thread.currentThread().getName() + " is running.");

        // 创建子线程对象
        Thread thread = new MultiThreadWay2();

        // 启动子线程
        thread.start();

    }
}
