package threads_review;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithoutSync {
    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i=0; i<100; i++){
            executorService.execute(new AddPennyTask());
        }
        executorService.shutdown();

        while (!executorService.isTerminated()){}

        System.out.println("Balance: "+account.getBalance());
    }


    private static class AddPennyTask implements Runnable{
        @Override
        public void run() {
            account.deposit(1);
        }
    }


    private static class Account {
        private int balance = 0;
        Semaphore semaphore = new Semaphore(1);


        public int getBalance() {
            return balance;
        }

        public void deposit(int amount){
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int newBalance = balance + amount;
            try {
                Thread.sleep(10);
                balance = newBalance;
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                semaphore.release();
            }

        }

    }
}
