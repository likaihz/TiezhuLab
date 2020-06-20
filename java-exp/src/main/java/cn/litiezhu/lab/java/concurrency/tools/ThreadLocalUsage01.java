package cn.litiezhu.lab.java.concurrency.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1000个打印日期的任务，用线程池执行。每个任务中都创建一个SimpleDateFormat实例。
 * @author Li Kai
 */
public class ThreadLocalUsage01 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 1000; i++) {
            int finalI = i;
            pool.submit(() -> {
                String date = new ThreadLocalUsage01().date(finalI);
                System.out.println(date);
            });
        }
        pool.shutdown();
    }
    public String date(int seconds) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(1000*seconds);
        return format.format(date);
        
    }
}