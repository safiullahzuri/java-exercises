package aggregate;

import java.util.Scanner;
import java.util.stream.DoubleStream;

public class AnalyzeNumbersUsingStream {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of items: ");

        int n = input.nextInt();

        double[] numbers = new double[n];
        double sum = 0;

        System.out.println("Enter the numbers: ");
        for (int i=0; i<n; i++){
            numbers[i] = input.nextDouble();
        }

        double average = DoubleStream.of(numbers).average().getAsDouble();

        System.out.println("Average is "+average);
        System.out.println("Number of elements above the average is "+DoubleStream.of(numbers).filter(e -> e>average).count());

    }

}
