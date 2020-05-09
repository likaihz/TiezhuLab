package cn.litiezhu.lab.java.concurrency;

/**
 * 处理中断最佳实践2：如果被调用的方法也要对中断进行处理，那么
 * 在catch块中处理完毕后要记得再恢复中断，以便让调用方也发现中断
 *（因为在方法内已处理过的中断状态将会被清除）。
 *
 * @author Li Kai
 */
public class RecoverInterrupt implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("before invoke");
            // 调用这个方法能够导致线程被中断
            recoverInMethod();
            if (Thread.currentThread().isInterrupted()) {
                // 调用方处理中断
                System.out.println("Process exception in outer method.");
                // 根据需要，选择跳出循环
                break;
            }
        }
    }

    private void recoverInMethod() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // 在方法中处理中断
            System.out.println("Process exception in inner method.");
            // 处理完后再恢复
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RecoverInterrupt());
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

    }
}
