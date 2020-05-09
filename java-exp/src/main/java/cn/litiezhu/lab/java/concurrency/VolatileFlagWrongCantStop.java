package cn.litiezhu.lab.java.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 在生产者-消费者模型中用volatile的canceled变量作为标志位，用于停止生产者线程。
 * 但是在生产者线程陷入阻塞时，修改canceled变量是无法停止线程的。
 *
 * @author Li Kai
 */
public class VolatileFlagWrongCantStop {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue q = new ArrayBlockingQueue(10);

        Producer producer = new Producer(q);
        Thread pThread = new Thread(producer);
        pThread.start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(q);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了。");
            Thread.sleep(1000);
        }
        System.out.println("消费已经结束");

        // 在这里修改canceled字段时，生产者很有可能处于阻塞状态，那么这个修改就起不到作用。
        producer.cancled = true;
    }

    // 生产者类，向阻塞队列中添加数据，如果阻塞队列已满，则阻塞生产者线程
    public static class Producer implements Runnable {
        private BlockingQueue storage;
        public volatile boolean cancled = false;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num <= 10000 && !cancled) {
                    if (num % 100 == 0) {
                        storage.put(num);
                        System.out.println("Put " + num + " in storage.");
                    }
                    // 通过自增模拟生产时间
                    ++num;
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Producer ended.");
            }

        }
    }

    // 消费者类
    public static class Consumer {
        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        // 模拟消费者的消费过程，有5%的可能性返回false
        public boolean needMoreNums() {
            if (Math.random() > 0.95) {
                return false;
            }
            return true;
        }
    }
}

