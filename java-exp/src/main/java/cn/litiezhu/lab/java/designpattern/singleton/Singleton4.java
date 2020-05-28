package cn.litiezhu.lab.java.designpattern.singleton;

/**
 * 懒汉式（同步方法，线程安全的），不推荐
 *
 * @author Li Kai
 */
public class Singleton4 {
    private static Singleton4 INSTANCE;

    private Singleton4() {

    }

    // 同步方法，效率低
    public synchronized static Singleton4 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton4();
        }
        return INSTANCE;
    }
}
