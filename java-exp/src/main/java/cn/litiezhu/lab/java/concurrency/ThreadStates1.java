package cn.litiezhu.lab.java.concurrency;

/**
 * 证明Thread对象的NEW，RUNNABLE和TERMINATED状态。
 *
 * @author Li Kai
 */
public class ThreadStates1 implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadStates1());
        // 打印出start之前的状态
        System.out.println("线程开始之前的状态："+ thread.getState());

        thread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打印出线程结束后的状态
        System.out.println("线程结束后的状态：" + thread.getState());

    }
    @Override
    public void run() {
        // 打印出线程运行时的状态
        System.out.println("线程运行时的状态：" + Thread.currentThread().getState());
    }
}
