package threads_review.exercises.ex8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadCooperation {
    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new DepositTask());
        service.execute(new WithdrawTask());



    }

    public static class DepositTask implements Runnable{
        @Override
        public void run() {
            try {
                while (true){
                    account.deposit((int)(Math.random()*10)+1);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class WithdrawTask implements Runnable{

        @Override
        public void run() {
            while (true){
                account.withdraw((int)(Math.random()*10)+1);
            }
        }
    }



    public static class Account {
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public synchronized void deposit(int amount){
            balance += amount;
            System.out.println("Deposit "+amount+"\t\t\t\t"+getBalance());
            notifyAll();
        }

        public synchronized void withdraw(int amount){
            try{
                while (balance < amount){
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println("\t\t\tWithdraw "+amount+"\t\t"+getBalance());
        }

    }

}
