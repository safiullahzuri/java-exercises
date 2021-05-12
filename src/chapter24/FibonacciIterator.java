package chapter24;

import java.util.Iterator;

public class FibonacciIterator implements Iterator<Long>{

    public static void main(String[] args) {
         FibonacciIterator fibIterator = new FibonacciIterator(1000);
         while (fibIterator.hasNext()){
            System.out.println(fibIterator.next());
        }
    }

    private long f1;
    private long f2;
    private long limit;
    private int iteration;

    public FibonacciIterator(long limit){
        this.limit = limit;
        f1 = 0;
        f2 = 1;
        iteration = 0;
    }

    @Override
    public boolean hasNext() {
        return (f1+f2) < limit;
    }

    @Override
    public Long next() {
        long fib;
        if (iteration == 0) fib =f1;
        else if (iteration == 1) fib = f2;
        else{
            fib = f1+f2;
            f1 = f2;
            f2 = fib;
        }
        iteration++;
        return new Long(fib);
    }
}
