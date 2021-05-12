package threads_review.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ex4 {

    static Ex4 ex4 = new Ex4();

    private int sum = 0;

    public synchronized void increaseSum(){
        sum++;
    }

    static class AddSum implements Runnable{

        @Override
        public void run() {
            ex4.increaseSum();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i=0; i<1000; i++){
            service.execute(new AddSum());
        }
        service.shutdown();
        while (!service.isTerminated()){}
        System.out.println("sum is "+ ex4.sum);
    }


}
