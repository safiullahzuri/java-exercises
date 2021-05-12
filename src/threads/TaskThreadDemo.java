package threads;

public class TaskThreadDemo {

    public static void main(String[] args) throws InterruptedException {

        Runnable printA = new PrintChar('A', 20);
        Runnable printB = new PrintChar('B', 20);
        Runnable print100 = new PrintNum(100, 20);

        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);

        thread1.start();
        thread2.start();
        thread3.start();
    }


    static class PrintChar implements Runnable{
        private char charToPrint;
        private int times;

        public PrintChar(char c, int t){
            charToPrint = c;
            times = t;
        }

        @Override
        public void run() {
            for (int i=0; i<times; i++){
                System.out.print(charToPrint+" ");
            }
        }
    }

    static class PrintNum implements Runnable{
        private int numToPrint;
        private int times;

        public PrintNum(int num, int times){
            this.numToPrint = num;
            this.times = times;
        }

        @Override
        public void run() {
            for (int i=0; i<times; i++){
                System.out.print(numToPrint+" ");
            }
        }
    }
}
