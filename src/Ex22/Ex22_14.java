package Ex22;

public class Ex22_14 {

    public static void main(String[] args) {
        System.out.println(UsingListing22_5(100000));
        System.out.println(UsingListing22_6(100000));
        System.out.println(UsingListing22_7(100000));
    }


    private static long UsingListing22_5(long under){
        long start = System.currentTimeMillis();
        int number = 2;

        for (int i=4; i<under; i++){
            isPrimeListing22_5(i);
        }
        long end = System.currentTimeMillis();
        return (end-start);
    }


    private static boolean isPrimeListing22_5(long n){
        for (int i=2; i<n/2; i++){
            if (n%2 == 0){
                return false;
            }
        }
        return true;
    }

    private static long UsingListing22_6(long under){
        long start = System.currentTimeMillis();
        int number = 2;

        for (int i=4; i<under; i++){
            isPrimeListing22_6(i);
        }
        long end = System.currentTimeMillis();
        return (end-start);
    }

    private static boolean isPrimeListing22_6(long n){
        double sqrt =  Math.sqrt(n);
        for (int i=4; i<sqrt; i++){
            if (n%i == 0){
                return false;
            }
        }
        return true;
    }

    private static long UsingListing22_7(int under){
        long start = System.currentTimeMillis();

        boolean[] primes = new boolean[under+1];
        for (int i=0; i<primes.length; i++){
            primes[i] = true;
        }

        for (int k=2; k<under/k; k++){
            if (primes[k]){

                for (int i=k; i<under/k; i++){
                    primes[i * k] = false;
                }

            }
        }



        long end = System.currentTimeMillis();
        return (end-start);
    }



}
