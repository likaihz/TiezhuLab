package cn.litiezhu.lab.java.concurrency;

/**
 * 探究主线程、子线程的id。
 *
 * @author Li Kai
 */
public class ThreadId {
    public static void main(String[] args) {
        Thread thread1 = new Thread();
        Thread thread2 = new Thread();
        // 主线程的tid是1
        System.out.println("主线程ID：" + Thread.currentThread().getId());
        // 子线程的tid已经从11开始了
        System.out.println("子线程1的ID：" + thread1.getId());
        System.out.println("子线程2的ID：" + thread2.getId());
    }
}
