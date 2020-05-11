package cn.litiezhu.lab.java.concurrency;

/**
 * @author Li Kai
 */
public class ThreadStates implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadStates());
        // 打印出start之前的状态
        System.out.println("状态1："+ thread.getState());

        thread.start();

        System.out.println("状态2：" + thread.getState());

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("状态3：" + thread.getState());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("状态4：" + thread.getState());

    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
