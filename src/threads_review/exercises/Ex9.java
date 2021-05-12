package threads_review.exercises;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex9 {

    static Set<Integer> set = Collections.synchronizedSet(new HashSet<>());


    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true){
                set.add((int)(Math.random()* 10000)+1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true){
                synchronized (set){
                    Iterator<Integer> iterator = set.iterator();
                    while (iterator.hasNext()){
                        System.out.println(iterator.next());
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }



}
