package cn.litiezhu.lab.java.concurrency.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1000个打印日期的任务，用线程池执行。所有线程共享一个SimpleDateFormat对象。
 * 由于SimpleDateFormat是一个线程不安全的类，因此会得到一些重复的结果。
 *
 * @author Li Kai
 */
public class ThreadLocalUsage02 {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            pool.submit(() -> {
                System.out.println(new ThreadLocalUsage02().date(finalI));
            });
        }
        pool.shutdown();

    }

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        return format.format(date);
    }

}
