package Ex21;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Ex21_15 {

    public static void main(String[] args) {
        int number1 = (int)(Math.random()*10);
        int number2 = (int)(Math.random()*10);

        Scanner input = new Scanner(System.in);

        System.out.printf("what is %d + %d?", number1, number2);

        Set<Integer> answers = new HashSet<>();

        int answer = input.nextInt();

        while (number1+number2 != answer){
            if (answers.contains(answer)){
                System.out.println("You already entered this.");
            }
            System.out.println("Wrong answer. Try again!");
            answers.add(answer);
            answer = input.nextInt();
        }
        System.out.println("You got it right.");
    }
}
