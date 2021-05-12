package bin_packing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BestFit {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of objects: ");
        Integer[] list = new Integer[input.nextInt()];

        System.out.println("Enter the weight of the objects: ");

        for (int i=0; i<list.length; i++){
            list[i] = input.nextInt();
        }

        ArrayList<Integer> objects = new ArrayList<>(Arrays.asList(list));
        objects.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1.compareTo(o2) > 0){
                    return -1;
                }else if (o1.compareTo(o2) < 0){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
        ArrayList<ArrayList<Integer>> containers = new ArrayList<>();


        while (!objects.isEmpty()){
            if (containers.isEmpty()){
                containers.add(new ArrayList<>());
                containers.get(containers.size()-1).add(objects.remove(0));
            }else{

                for (int i=0; i<containers.size(); i++){
                    int sum = 0;
                    for (int j=0; j<containers.get(i).size(); j++){
                        sum += containers.get(i).get(j);
                    }

                    int spaceRemaining = 10 - sum;

                    for (int k=0; k< objects.size(); k++){
                        if (objects.get(k) <= spaceRemaining){
                            containers.get(i).add(objects.get(k));
                            spaceRemaining -= objects.get(k);
                            objects.remove(k);
                            k--;
                        }
                    }

                    if (i == containers.size()-1 && !objects.isEmpty()){
                        containers.add(new ArrayList<>());
                    }


                }


            }
        }




        for (int i = 0; i < containers.size(); i++) {
            System.out.print("Container " + (i + 1) +
                    " contains objects with weight ");
            for (int j = 0; j < containers.get(i).size(); j++) {
                System.out.print(containers.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println("The optimal number of bins is " + containers.size());


    }

}
