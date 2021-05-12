package Ex22;

import java.util.Scanner;

public class Ex22_02 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter n:");
        int n = input.nextInt();

        System.out.println("Enter x: ");
        int x = input.nextInt();

        int[] coefficients = new int[n+1];

        for (int i=0; i<coefficients.length; i++){
            System.out.println("Enter a"+i);
            coefficients[i] = input.nextInt();
        }

        int result = 0;
        for (int i=0; i<coefficients.length; i++){
            result += coefficients[i]*Math.pow(x, i);
        }
        System.out.println(result);

    }
}
