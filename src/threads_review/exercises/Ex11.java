package threads_review.exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex11 {

    public static void main(String[] args) {
        String str1 = "";
        String str2 = "";

        Thread thread1 = new Thread(() -> {
            synchronized (str1){
                System.out.println("str1 accessed by thread1");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (str2){
                    System.out.println("str2 accessed by thread1");
                }
            }

        });

        Thread thread2 = new Thread(() -> {
           synchronized (str2){
               System.out.println("str2 accessed by thread2");

               synchronized (str1){
                   System.out.println("str1 accessed by thread1");
               }
           }
        });
        thread1.start();
        thread2.start();
    }
}
