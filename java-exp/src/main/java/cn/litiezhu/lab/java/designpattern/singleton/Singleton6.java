package cn.litiezhu.lab.java.designpattern.singleton;

/**
 * 双重检查，单例模式。
 *
 * @author Li Kai
 */
public class Singleton6 {
    // 为了保证线程安全
    // 因为对象的实例化与赋值实际上要经过3个步骤
    // 1. 创建一个空对象
    // 2. 调用构造方法
    // 3. 将引用赋值给变量INSTANCE
    // 而在执行时，这些步骤有可能被重排序，如以1，3，2的顺序执行
    // 在这种情况下，线程1在执行完3时INSTANCE虽然不是null，但是指向的实际上是一个空对象，
    // 如果这时线程2调用了getInstance()方法，那么就会直接把这个空对象发布，造成逸出
    // 因此，这里要用volatile修饰，禁止重排序
    private volatile static Singleton6 INSTANCE;

    private Singleton6() {

    }

    public static Singleton6 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton6.class) {
                // 进入同步代码块后再一次进行判断，检查是否有其他线程已经创建了实例
                if (INSTANCE == null) {
                    INSTANCE = new Singleton6();
                }
            }
        }
        return INSTANCE;
    }
}
