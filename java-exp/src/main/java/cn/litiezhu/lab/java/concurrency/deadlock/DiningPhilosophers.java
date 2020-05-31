package cn.litiezhu.lab.java.concurrency.deadlock;

/**
 * @author Li Kai
 */
public class DiningPhilosophers {
    public static class Philosopher implements Runnable {

        private Object leftChopstick, rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            while (true) {
                doAction("think");
                synchronized (leftChopstick) {
                    doAction("Picking up left chopstick");
                    synchronized (rightChopstick) {
                        doAction("Picking up right chopstick - eating");

                        doAction("Putting down right chopstick");
                    }

                    doAction("Putting down left chopstick");
                }
            }
        }

        private void doAction(String action) {
            System.out.println(Thread.currentThread().getName() + " in action: " + action);

            try {
                Thread.sleep((long) (Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public static void main(String[] args) {
            Philosopher[] philosophers = new Philosopher[5];

            Object[] chopsticks = new Object[philosophers.length];

            for (int i = 0; i < philosophers.length; i++) {
                chopsticks[i] = new Object();
            }

            for (int i = 0; i < philosophers.length; i++) {
                philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i + 1) % philosophers.length]);
            }

            for (int i = 1; i <= philosophers.length; i++) {
                new Thread(philosophers[i-1], "Philosopher" + i).start();
            }
        }
    }
}
