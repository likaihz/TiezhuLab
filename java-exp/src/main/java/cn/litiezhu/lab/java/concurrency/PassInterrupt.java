package cn.litiezhu.lab.java.concurrency;

/**
 * 处理中断最佳实践1：如果被调用的方法有阻塞存在并且不需要处理中断，
 * 那么就直接将中断异常传递出去，让调用方来处理。
 *
 * @author Li Kai
 */
public class PassInterrupt implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("before invoke");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                System.out.println("Outer method catch exception.");

                // 根据逻辑需要，如果需要在被中断后跳出循环就要在这里break，
                // 而不能在while的条件中判断中断状态，因为中断异常被捕获以后，
                // 中断状态已经被清除了。
                break;
            }
        }
    }

    // 可能抛出中断异常的方法，
    private void throwInMethod() throws InterruptedException {
        // 对可能产生的中断异常不做任何处理，直接抛出
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new PassInterrupt());
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
