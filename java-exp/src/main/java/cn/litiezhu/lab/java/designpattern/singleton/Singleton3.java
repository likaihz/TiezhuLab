package cn.litiezhu.lab.java.designpattern.singleton;

/**
 * 懒汉式单例模式，线程不安全，不可用
 *
 * @author Li Kai
 */
public class Singleton3 {
    private static Singleton3 INSTANCE;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        // 如果两个线程同时调用，可能导致实例化两次
        if (INSTANCE == null) {
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }
}
