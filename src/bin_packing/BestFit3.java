package bin_packing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BestFit3 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        System.out.println("Enter objects: ");

        Integer[] list = new Integer[n];

        for (int i=0; i<list.length; i++){
            list[i] = input.nextInt();
        }

        System.out.println("done here");
        ArrayList<Integer> objects = new ArrayList<>(Arrays.asList(list));

        ArrayList<ArrayList<Integer>> containers = new ArrayList<>();

        while (!objects.isEmpty()){
            if (containers.isEmpty()){
                containers.add(new ArrayList<>());
                containers.get(containers.size()-1).add(objects.remove(0));
            }else{
                for (int i=0; i<containers.size(); i++){

                    int sum = 0;

                    for (int k=0; k<containers.get(i).size(); k++){
                        sum += containers.get(i).get(k);
                    }

                    int spaceRemaining = 10 - sum;
                    for (int j=0; j<objects.size();j++){
                        if (objects.get(j) <= spaceRemaining){
                            containers.get(i).add(objects.get(j));
                            spaceRemaining -= objects.get(j);
                            objects.remove(j);
                            j--;
                        }
                    }

                    if (i == containers.size()-1 && !objects.isEmpty()){
                        containers.add(new ArrayList<>());
                    }

                }
            }
        }

        for (int i=0; i<containers.size(); i++){
            System.out.println("Container "+(i+1)+" contains elements "+containers.get(i));
        }



    }

}
