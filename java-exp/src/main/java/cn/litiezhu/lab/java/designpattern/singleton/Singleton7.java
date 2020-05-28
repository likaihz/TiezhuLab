package cn.litiezhu.lab.java.designpattern.singleton;

/**
 * 静态内部类实现单例模式，可用
 * 属于懒汉式
 *
 * @author Li Kai
 */
public class Singleton7 {
    private Singleton7() {

    }

    public static Singleton7 getInstance() {
        return Singleton7Instance.INSTANCE;
    }

    private static class Singleton7Instance {

        private static final Singleton7 INSTANCE = new Singleton7();
    }
}
