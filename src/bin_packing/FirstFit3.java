package bin_packing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FirstFit3 {

    public static void main(String[] args) {
        System.out.println("Enter the number of objects: ");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();

        System.out.println("Enter objects: ");
        int[] objects = new int[number];

        for (int i=0; i<number; i++){
            objects[i] = input.nextInt();
        }

        ArrayList<ArrayList<Integer>> containers = new ArrayList<>();

        for (int i=0; i<objects.length; i++){
            if (containers.isEmpty()){
                containers.add(new ArrayList<>());
                containers.get(containers.size()-1).add(objects[i]);
            }else{
                for (int j=0; j<containers.size(); j++){
                    int sum = 0;

                    for (int k=0; k<containers.get(j).size(); k++){
                        sum += containers.get(j).get(k);
                    }

                    if (sum + objects[i] <= 10){
                        containers.get(j).add(objects[i]);
                        break;
                    }else if (j == containers.size()-1){
                        containers.add(new ArrayList<>());
                        containers.get(containers.size()-1).add(objects[i]);
                        break;
                    }

                }
            }
        }

        for (int i=0; i<containers.size(); i++){
            System.out.print("Container "+(i+1)+" contains objects "+containers.get(i));
            System.out.println();
        }

    }
}
