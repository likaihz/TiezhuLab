package cn.litiezhu.lab.java.concurrency;

/**
 * 自定义的UncaughtExceptionHandler，仅仅是在发现异常时打印出一行提醒。
 *
 * @author Li Kai
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("["+name+"]线程异常终止：" + t.getName());
    }
}
