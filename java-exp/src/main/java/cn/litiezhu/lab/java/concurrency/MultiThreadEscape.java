package cn.litiezhu.lab.java.concurrency;

import java.util.HashMap;
import java.util.Map;

/**
 * 多线程安全问题3：对象的发布与逸出
 *
 * @author Li Kai
 */
public class MultiThreadEscape {
    private Map<String, String> states;

    public MultiThreadEscape() {
        this.states = new HashMap<>();

        states.put("1", "one");
    }

    public Map<String, String> getStates() {
        // 直接返回states对象，就是一种逸出（escape）情况
        return states;
    }

    public Map<String, String> getStatesCopy() {
        // 返回一个副本，可以实现禁止外部对私有对象的修改
        return new HashMap<>(states);
    }
}
