package Ex22;

import java.util.Scanner;

public class SieveOfErathosthens {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        boolean[] primes = new boolean[n];

        for (int i=0; i<primes.length; i++){
            primes[i] = true;
        }

        for (int k=2; k<=n/k; k++){
            System.out.println("K is "+k);
            if (primes[k]){
                for (int i=k; i<=n/k; i+=k){
                    primes[i] = false;
                }
            }
        }

        for (int i=0; i<primes.length; i++){
            if (primes[i]){
                System.out.printf("%7d", i);
            }
        }

    }
}
