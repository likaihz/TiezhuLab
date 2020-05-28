package cn.litiezhu.lab.java.designpattern.singleton;

/**
 * 饿汉式单例模式（静态代码块），线程安全的
 *
 * @author Li Kai
 */
public class Singleton2 {

    private final static Singleton2 INSTANCE;

    static {
        INSTANCE = new Singleton2();
    }

    // 禁止外界实例化
    private Singleton2() {

    }

    public Singleton2 getInstance() {
        return INSTANCE;
    }


}
