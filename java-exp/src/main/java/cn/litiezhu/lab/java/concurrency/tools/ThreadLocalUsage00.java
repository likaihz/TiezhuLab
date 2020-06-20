package cn.litiezhu.lab.java.concurrency.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ThreadLocal使用之：不使用ThreadLocal，而是每次使用时都新建一个实例。
 * 但是多余的实例化操作会带来额外的开销。
 * 
 * @author Li Kai
 */
public class ThreadLocalUsage00 {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(new ThreadLocalUsage00().date(10));
        }).start();

        new Thread(() -> {
            System.out.println(new ThreadLocalUsage00().date(10000));
        }).start();

    }

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
        
    }
}