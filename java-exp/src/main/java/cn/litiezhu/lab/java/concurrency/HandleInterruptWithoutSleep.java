package cn.litiezhu.lab.java.concurrency;

/**
 * 在线程没有阻塞的情况下使用interrupt中断该线程，如果该线程在发现被中断后
 * 主动响应，则能够结束run方法
 *
 * @author Li Kai
 */
public class HandleInterruptWithoutSleep implements Runnable {
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        int num = 0;
        // 循环条件中加入对中断的判断，如果线程已经被中断，则结束循环。
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num);
            }
            ++num;
        }

        System.out.println("运行结束，用时 " + (System.currentTimeMillis()-start) + " ms.");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new HandleInterruptWithoutSleep());
        thread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
