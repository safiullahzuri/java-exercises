package threads_review;

public class TestPrint {

    public static void main(String[] args) {
        Thread thread = new Thread(new PrintChar('a', 100));
        Thread thread1 = new Thread(new PrintChar('b', 100));
        Thread thread2 = new Thread(new PrintNum(99, 100));

        thread.start();
        thread1.start();
        thread2.start();
    }

}
