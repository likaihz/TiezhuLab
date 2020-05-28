package cn.litiezhu.lab.java.designpattern.singleton;

/**
 * 懒汉式单例模式（线程不安全，同步代码块），不可用
 *
 * @author Li Kai
 */
public class Singleton5 {
    private static Singleton5 INSTANCE;

    private Singleton5() {

    }

    // 同步代码块，并没有解决线程安全问题
    public static Singleton5 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton5.class) {
                INSTANCE = new Singleton5();
            }
        }
        return INSTANCE;
    }
}
