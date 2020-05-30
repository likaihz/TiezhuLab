package cn.litiezhu.lab.java.concurrency.deadlock;

import java.util.Random;

/**
 * 修复多人转账时可能出现死锁的问题：死锁避免策略
 *
 * @author Li Kai
 */
public class MultiTransferMoneyFixed {
    private static final int NUM_ACCOUNTS = 500;
    private static final int NUM_MONEY = 1000;
    private static final int NUM_ITERATION = 1000000;
    private static final int NUM_THREADS = 20;

    public static void main(String[] args) {
        Random rnd = new Random();

        TransferMoney.Account[] accounts = new TransferMoney.Account[NUM_ACCOUNTS];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new TransferMoney.Account(NUM_MONEY);
        }

        Runnable runnable = () -> {
            for (int i = 0; i < NUM_ITERATION; i++) {
                int from = rnd.nextInt(NUM_ACCOUNTS);
                int to = rnd.nextInt(NUM_ACCOUNTS);
                int amt = rnd.nextInt(NUM_MONEY);
                transfer(accounts, from, to, amt);
            }
        };

        for (int i = 0; i < NUM_THREADS; i++) {
            new Thread(runnable).start();
        }

        System.out.println("====== 程序运行结束 ======");
    }

    private static void transfer(TransferMoney.Account[] accounts, int from, int to, int amount) {
        TransferMoney.Account fromAcc = accounts[from];
        TransferMoney.Account toAcc = accounts[to];
        TransferMoney.Account lock1, lock2;
        if (from < to) {
            lock1 = fromAcc;
            lock2 = toAcc;
        } else {
            lock1 = toAcc;
            lock2 = fromAcc;
        }
        // 通过id保证获取锁的顺序
        synchronized (lock1) {
            synchronized (lock2) {
                if (fromAcc.balance - amount < 0) {
                    System.out.println("余额不足，转账失败。");
                } else {
                    fromAcc.balance -= amount;
                    toAcc.balance += amount;
                    System.out.println("成功转账" + amount + "元。");
                }
            }
        }
    }
}
