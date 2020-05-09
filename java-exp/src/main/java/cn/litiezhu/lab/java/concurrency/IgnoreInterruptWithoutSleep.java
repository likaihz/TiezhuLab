package cn.litiezhu.lab.java.concurrency;


/**
 * 在线程没有阻塞的情况下使用interrupt中断该线程，如果该线程并没有检查中断信号，
 * 则中断不起作用。
 *
 * @author Li Kai
 */
public class IgnoreInterruptWithoutSleep implements Runnable {
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        int num = 0;
        while (num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num);
            }
            ++num;
        }

        System.out.println("运行结束，用时 " + (System.currentTimeMillis()-start) + " ms.");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new IgnoreInterruptWithoutSleep());
        thread.start();

        Thread.sleep(200);
        thread.interrupt();
    }
}
