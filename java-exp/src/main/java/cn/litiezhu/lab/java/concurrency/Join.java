package cn.litiezhu.lab.java.concurrency;

/**
 * @author Li Kai
 */
public class Join implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Join runnable = new Join();
        Thread thread1 = new Thread(runnable);

        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        // 如果将下面两行删除，会得到不一样的结果
        thread1.join();
        thread2.join();

        System.out.println("所有子线程执行完毕。");
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始执行。");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行完毕。");
    }
}
