package cn.litiezhu.lab.java.concurrency;

/**
 * 演示线程阻塞在join方法时的状态为WAITING
 *
 * @author Li Kai
 */
public class ThreadStateInJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();

        Thread thread = new Thread(() -> {
            try {
                System.out.println("子线程开始运行");
                Thread.sleep(3000);
                System.out.println("主线程状态为" + mainThread.getState());
                System.out.println("子线程运行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join();
    }
}
