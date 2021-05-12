package chapter24;

import java.util.ArrayList;
import java.util.Iterator;

public class PrimeIterator2 implements Iterator<Long> {

    public static void main(String[] args) {
        PrimeIterator2 primeIterator = new PrimeIterator2(1000);
        final int PRIMES_PER_LINE = 10;
        int count = 0;
        while (primeIterator.hasNext()) {
            if (count % PRIMES_PER_LINE == 0) { System.out.println(); }
            System.out.printf("%5d ", primeIterator.next());
            count++;
        }
        System.out.println();
    }

    private long limit;
    private long candidate;
    private ArrayList<Long> primes;

    public PrimeIterator2(long limit){
        this.limit = limit;
        candidate = 2L;
        primes = new ArrayList<>();
    }




    @Override
    public boolean hasNext() {
        while (!isPrime(candidate)){
            candidate++;
        }
        return candidate<=limit;
    }

    @Override
    public Long next() {
        primes.add(candidate);
        return new Long(candidate++);
    }

    private boolean isPrime(long n){
        if (n==2) return true;
        if (n%2 == 0) return false;
        for (int i=0; primes.get(i) <= Math.sqrt(n); i++){
            if (n%primes.get(i) == 0) return false;
        }
        return true;
    }

}
