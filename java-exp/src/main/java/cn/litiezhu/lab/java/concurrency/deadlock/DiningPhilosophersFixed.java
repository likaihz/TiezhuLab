package cn.litiezhu.lab.java.concurrency.deadlock;

/**
 * @author Li Kai
 */
public class DiningPhilosophersFixed {
    public static void main(String[] args) {
        DiningPhilosophers.Philosopher[] philosophers = new DiningPhilosophers.Philosopher[5];

        Object[] chopsticks = new Object[philosophers.length];

        for (int i = 0; i < philosophers.length; i++) {
            chopsticks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            // 把最后一个哲学家的左右调换一下
            if(i == philosophers.length-1)philosophers[i] = new DiningPhilosophers.Philosopher(chopsticks[0], chopsticks[i-1]);
            else philosophers[i] = new DiningPhilosophers.Philosopher(chopsticks[i], chopsticks[i + 1]);
        }

        for (int i = 1; i <= philosophers.length; i++) {
            new Thread(philosophers[i-1], "Philosopher" + i).start();
        }
    }
}
