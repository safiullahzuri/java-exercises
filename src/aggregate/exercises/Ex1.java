package aggregate.exercises;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ex1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of students: ");
        int n = input.nextInt();

        System.out.printf("Enter %d scores\n", n);

        int[] scores = new int[n];
        for (int i=0; i<n; i++){
            scores[i] = input.nextInt();
        }

        IntStream.of(scores).forEach(value -> {
            if (value>90){
                System.out.printf("Student %d score %d grade A.\n", getIndex(value, scores), value);
            }else if (value>80){
                System.out.printf("Student %d score %d grade B.\n", (getIndex(value, scores)), value);
            }else if (value>70){
                System.out.printf("Student %d score %d grade C.\n", getIndex(value, scores), value);
            }else{
                System.out.printf("Student %d score %d grade F.\n", getIndex(value, scores), value);
            }
        });

    }

    private static int getIndex(int e, int[] scores){
        for (int i=0; i<scores.length; i++){
            if (scores[i] == e)
                return i;
        }
        return -1;
    }


}
