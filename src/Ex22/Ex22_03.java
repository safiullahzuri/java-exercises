package Ex22;

import java.util.Scanner;

public class Ex22_03 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter string s1:");
        String s1 = input.nextLine();

        System.out.println("Enter string s2:");
        String s2 = input.nextLine();

        for (int i=0; i<s1.length()-s2.length()+1; i++){
            if (s1.substring(i, i+s2.length()).equals(s2)){
                System.out.println("Matched at index "+i);
                break;
            }
        }
    }
}
