package Ex22;

import java.io.*;
import java.util.Scanner;

public class Ex22_10 {

    public static void main(String[] args) throws Exception{
        try(DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("file.dat"))) {
             for (int i=1; i<100000; i++){
                 if (isPrime(i)){
                     dataOutputStream.writeLong(i);
                 }
             }
        }


        System.out.println("Enter the number:");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        try(DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream("file.dat")))){
            long n;
            while ((n = dataInputStream.readLong()) != -1 && n<number){
                System.out.println(n);
            }
        }catch (EOFException eofException){
            System.out.println("end of file");
        }


    }

    public static boolean isPrime(long n){
        long sqrt = (long) Math.sqrt(n);
        for (int i=2; i<=sqrt; i++){
            if (n%i == 0)return false;
        }
        return true;
    }
}
