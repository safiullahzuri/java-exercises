package chapter24;

import java.util.Iterator;

public class PrimeNumberIterator implements Iterator<Integer> {

    public static void main(String[] args) {
        PrimeNumberIterator primeNumberIterator = new PrimeNumberIterator(100);
        while (primeNumberIterator.hasNext()){
            System.out.println(primeNumberIterator.next());
        }
    }


    private int limit;
    private int numberToCheck;

    public PrimeNumberIterator(int limit){
        this.limit = limit;
        numberToCheck = 1;
    }


    @Override
    public boolean hasNext() {
        return numberToCheck<limit;
    }

    @Override
    public Integer next() {
        if (numberToCheck == 1 || numberToCheck ==2){
            return numberToCheck++;
        }else{
            while (!isPrime(numberToCheck) && numberToCheck <= limit){
                numberToCheck++;
            }
        }
        return numberToCheck++;
    }

    private boolean isPrime(int n){
        for (int i=2; i<=Math.sqrt(n); i++){
            if (n%i == 0){
                return false;
            }
        }
        return true;
    }



}
