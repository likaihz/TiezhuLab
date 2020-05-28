package cn.litiezhu.lab.java.designpattern.singleton;

/**
 * 饿汉式单例模式（静态常量），线程安全的
 * @author Li Kai
 */
public class Singleton1 {
    // 类在加载时就已经实例化
    private final static Singleton1 INSTANCE = new Singleton1();

    // 禁止外界实例化
    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
