package Ex22;

import java.io.File;
import java.io.IOException;
import java.io.EOFException;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class E22_08_v2 {
    public static void main(String[] args) throws IOException {
        String filename = "PrimeNumbers.dat";
        File file = new File(filename);

        // Write 2 to PrimeNumbers.dat to begin with
        if (!file.exists()) {
            try (
                    DataOutputStream output =
                            new DataOutputStream(
                                    new FileOutputStream(file));
            ) {
                output.writeLong(2);
            }
        }

        // Obtain the current value to check for primeness (i.e. the last value
        // in PrimeNumbers.dat + 1)
        long value = -1;
        try (
                DataInputStream input =
                        new DataInputStream(
                                new BufferedInputStream(
                                        new FileInputStream(file)));
        ) {
            while (true) {
                value = input.readLong() + 1;
                if (value % 2 == 0) { value++; }
            }
        } catch (EOFException ex) {
            // Do nothing
        }

        outer:
        for (long i = value; i <= 10_000_000_000L; i += 2) {
            long[] arr = new long[0];
            boolean prime = true;
            try (
                    DataInputStream input =
                            new DataInputStream(
                                    new BufferedInputStream(
                                            new FileInputStream(file)));
            ) {
                while (true) {
                    // get up to 10,000 primes from the file
                    arr = new long[10_000];
                    for (int j = 0; j < arr.length; j++) {
                        arr[j] = input.readLong();
                    }
                    // if number of primes to test >= 10_000, process a full chunk here
                    if (!isPrime(i, arr)) {
                        prime = false;
                        continue outer;
                    }
                }
            } catch (EOFException ex) {
                // if EOF has been reached, process number of primes < 10_000 here
                if (!isPrime(i, arr)) {
                    prime = false;
                    continue outer;
                }
            }
            if (prime) {
                writePrime(i, file);
            }
        }
        System.out.println("done here");
    }

    private static boolean isPrime(long n, long[] arr) {
        double sqrt = Math.sqrt(n);
        for (int i = 0; i < arr.length && arr[i] != 0 && arr[i] <= sqrt; i++) {
            if (n % arr[i] == 0) { return false; }
        }
        return true;
    }

    private static void writePrime(long prime, File file) throws IOException {
        try (
                DataOutputStream output =
                        new DataOutputStream(
                                new FileOutputStream(file, true));
        ) {
            output.writeLong(prime);
        }
    }
}