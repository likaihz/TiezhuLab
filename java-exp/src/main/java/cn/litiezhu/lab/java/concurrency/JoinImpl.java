package cn.litiezhu.lab.java.concurrency;

/**
 * 通过学习join的原理，分析出join的代替写法
 *
 * @author Li Kai
 */
public class JoinImpl {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕。");
        });

        thread.start();
        System.out.println("开始等待子线程执行完毕");
        // 以下的同步代码块与thread.join()是一样的效果
        synchronized (thread) {
            thread.wait();
        }
        System.out.println("所有子线程执行完毕");
    }
}
