package cn.litiezhu.lab.java.concurrency.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用ThreadLocal给每个线程分配自己的dataFormat对象。
 * 保证线程安全的同时，又能够高效利用内存。
 *
 * @author Li Kai
 */
public class ThreadLocalUsage03 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        ThreadLocal<SimpleDateFormat> safeFormat = ThreadLocal.withInitial(() -> {
            System.out.println("New formatter.");
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        });

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            pool.submit(() -> {
                Date date = new Date(1000 * finalI);
                SimpleDateFormat format = safeFormat.get();
                System.out.println(format.format(date));
            });
        }
        pool.shutdown();

    }
}
