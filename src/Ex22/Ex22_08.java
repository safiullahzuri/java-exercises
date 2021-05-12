package Ex22;

import javax.xml.crypto.Data;
import java.io.*;

public class Ex22_08 {
    static final long TEN_MILLIONS = 10000000000l;

    public static void main(String[] args) throws Exception {
        String fileName = "primeNumbers6.dat";
        File file = new File(fileName);

        if (file.exists()){
            file.delete();
        }
        file.createNewFile();

        //populate 10,000 primes into the file

        populateFirst10Thousands(file);

        //load 10,000 starting from index i

        try(DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))){
            System.out.println(dis.readLong());
        }



    }

    private static void populateFirst10Thousands(File file) throws Exception{
        try(DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file, true))){
            int primes = 0;
            dataOutputStream.writeLong(2);
            long number = 3;
            while (primes<10000){
                if (isPrime(number)){
                    dataOutputStream.writeLong(number);
                    dataOutputStream.flush();
                    primes++;
                }
                number++;
            }
        }
    }

    private static long[] load10Thousands(File file, int indexFrom) throws Exception{
        long[] primes = new long[10000];
        try(DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))){

            for (int i=0;indexFrom > 0 && i<indexFrom+10000; i++){
                input.readLong();
            }

            for (int j=0; j<10000; j++){
                primes[j] = input.readLong();
            }
        }
        return primes;
    }

    private static long[] load10Thousands(File file) throws Exception{
        long[] primes = new long[10000];
        try(DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(file)))){
            for (int j=0; j<10000; j++){
                primes[j] = input.readLong();
            }
        }
        return primes;
    }

    public static boolean isPrime(long n){
        double sqrt = Math.sqrt(n);
        for (int i=2; i<sqrt; i++){
            if (n%i == 0)return false;
        }
        return true;
    }

    public static boolean isPrime(long n, long[] primes){
        double sqrt = Math.sqrt(n);
        for (int i=0; i<primes.length && primes[i] != 0 && primes[i] <= sqrt ; i++){
            if (n%i == 0)return false;
        }
        return true;
    }

    private static void writePrime(long prime, File file) throws IOException {
        try(DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file, true))){
            dataOutputStream.writeLong(prime);
        }
    }


}
