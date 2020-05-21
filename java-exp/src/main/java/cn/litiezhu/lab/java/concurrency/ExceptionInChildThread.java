package cn.litiezhu.lab.java.concurrency;

/**
 * 在单线程的情况下，抛出异常是有堆栈的
 * 但是在多线程的情况下，子线程发生异常，并不会影响主线程的运行
 *
 * @author Li Kai
 */
public class ExceptionInChildThread implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
        }
    }
}
