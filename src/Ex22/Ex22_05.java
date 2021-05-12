package Ex22;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex22_05 {

    public static void main(String[] args) {
        //longest sequence of the same integers

        List<Integer> numbers = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        int n;
        while ((n = input.nextInt()) != 0){
            numbers.add(n);
        }

        int index = 0;
        int value = numbers.get(index);
        int count = 1;

        int maxIndex = index;
        int maxValue = value;
        int maxCount = count;

        for (int i=1; i<numbers.size(); i++){
            if (numbers.get(i) == value){
                count++;
                if (count > maxCount){
                    maxCount = count;
                    if (maxIndex != index){maxIndex = index;}
                    maxValue = numbers.get(i);
                }
            }else{
                 value = numbers.get(i);
                 count = 1;
                 index = i;
            }
        }


        System.out.println("The longest same number sequence starts at index " +
                maxIndex + " with " + maxCount + " values of " + maxValue);

    }
}
