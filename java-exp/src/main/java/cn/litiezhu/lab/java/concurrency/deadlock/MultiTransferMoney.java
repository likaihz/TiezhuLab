package cn.litiezhu.lab.java.concurrency.deadlock;

import cn.litiezhu.lab.java.concurrency.deadlock.TransferMoney.Account;

import java.util.Random;

/**
 * 多人同时转账时，也可能发生死锁。
 * 只要运行时间够长，最终总会发生死锁的（墨菲定理）
 *
 * @author Li Kai
 */
public class MultiTransferMoney {
    private static final int NUM_ACCOUNTS = 500;
    private static final int NUM_MONEY = 1000;
    private static final int NUM_ITERATION = 1000000;
    private static final int NUM_THREADS = 20;

    public static void main(String[] args) {
        Random rnd = new Random();

        Account[] accounts = new Account[NUM_ACCOUNTS];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(NUM_MONEY);
        }

        Runnable runnable = () -> {
            for (int i = 0; i < NUM_ITERATION; i++) {
                int from = rnd.nextInt(NUM_ACCOUNTS);
                int to = rnd.nextInt(NUM_ACCOUNTS);
                int amt = rnd.nextInt(NUM_MONEY);
                TransferMoney.transfer(accounts[from], accounts[to], amt);
            }
        };

        for (int i = 0; i < NUM_THREADS; i++) {
            new Thread(runnable).start();
        }

        System.out.println("====== 程序运行结束 ======");
    }
}
