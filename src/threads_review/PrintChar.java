package threads_review;

public class PrintChar implements Runnable{

    char c;
    int t;

    public PrintChar(char c, int t){
        this.c = c;
        this.t = t;
    }


    @Override
    public void run() {

        for (int i=0; i<t; i++){
            System.out.print(c+" ");
        }
    }
}

class PrintNum implements Runnable{

    int num;
    int t;

    public PrintNum(int num, int t){
        this.num = num;
        this.t = t;
    }

    @Override
    public void run() {
        for (int i=0; i<t; i++){
            System.out.print(i+" ");
            if (i == 50){
                Thread.yield();
            }
        }
    }
}
