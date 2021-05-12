package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        executor.execute(new TaskThreadDemo.PrintChar('a', 1000));
        executor.execute(new TaskThreadDemo.PrintChar('b', 1000));
        executor.execute(new TaskThreadDemo.PrintNum(77, 1000));

        executor.shutdown();


    }

}
