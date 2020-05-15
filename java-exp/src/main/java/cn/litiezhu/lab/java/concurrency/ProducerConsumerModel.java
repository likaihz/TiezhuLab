package cn.litiezhu.lab.java.concurrency;

import java.util.Date;
import java.util.LinkedList;

/**
 * 用wait/notify来实现生产者消费者模型。
 *
 * @author Li Kai
 */
public class ProducerConsumerModel {
    public static void main(String[] args) {
        ProducerConsumerModel wrapper = new ProducerConsumerModel();

        EventStorage storage = wrapper.new EventStorage();
        Producer producer = wrapper.new Producer(storage);
        Consumer consumer = wrapper.new Consumer(storage);

        Thread pThread = new Thread(producer), cThread = new Thread(consumer);

        pThread.start();
        cThread.start();
    }

    // 仓库类
    class EventStorage {
        private int maxSize;
        private LinkedList<Date> storage;

        public EventStorage() {
            this.maxSize = 10;
            storage = new LinkedList<>();
        }

        public synchronized void put() {
            while (storage.size() == maxSize) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            storage.add(new Date());
            System.out.println("仓库中已有" + storage.size() + "个产品。");
            notify();
        }

        public synchronized void take() {
            while (storage.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("拿到了"+ storage.poll()+", 仓库中还剩下" + storage.size());
            notify();
        }
    }

    // 生产者类
    class Producer implements Runnable {

        private EventStorage storage;

        public Producer(EventStorage storage) {
            this.storage = storage;
        }
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.put();
            }
        }
    }

    // 消费者类
    class Consumer implements Runnable {

        private EventStorage storage;

        public Consumer(EventStorage storage) {
            this.storage = storage;
        }
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.take();
            }
        }
    }

}
