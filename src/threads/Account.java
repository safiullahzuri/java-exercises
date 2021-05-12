package threads;

import java.util.concurrent.Semaphore;

public class Account {
    private static Semaphore semaphore = new Semaphore(1);
    private int balance = 0;

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount){
        try {
            semaphore.acquire();
            int newBalance = balance + amount;
            Thread.sleep(5);
            balance = newBalance;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
