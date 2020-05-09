package cn.litiezhu.lab.java.concurrency;

/**
 * 用volatile的boolean变量作为标志位，用于停止线程。
 * 如果被停止的线程没有长时间的阻塞，这种方法是可行的。
 *
 * @author Li Kai
 */
public class VolatileFlag implements Runnable {

    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            // 每一次迭代之前判断canceled字段是否被修改为true
            while (!canceled && num <= 100000) {
                if (num % 100 == 0) {
                    System.out.println(num);
                }
                ++num;
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileFlag target = new VolatileFlag();
        Thread thread = new Thread(target);
        thread.start();
        Thread.sleep(5000);
        // 将线程的canceled字段修改为true让该线程停止
        target.canceled = true;
    }
}
