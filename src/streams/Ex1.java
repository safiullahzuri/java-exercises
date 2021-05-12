package streams;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ex1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of students: ");
        int n = input.nextInt();
        System.out.println("Enter "+n+" scores.");
        int[] scores = new int[n];
        for (int i=0; i<n; i++){
            scores[i] = input.nextInt();
        }

        int a = 0;
        IntStream.of(scores).forEach(s -> {
            char c = 'A';
            if (s > 90){
                c = 'A';
            }else if (s > 80)c = 'B';
            else if (s > 70) c='C';
            else if (c >60) c = 'D';
            else if (c < 50) c = 'F';
            else c = 'F';

            System.out.println("Student " + a+ " score is "+s+" and grade "+c);
         
        });

    }
}
