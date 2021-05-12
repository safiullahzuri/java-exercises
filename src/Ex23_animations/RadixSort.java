package Ex23_animations;

import java.util.ArrayList;

public class RadixSort {

    int[] list;
    public RadixSort(int[] list){
        this.list = list;
    }

    public void radixSortMe(){
        radixSort(this.list);
    }


    private static void printArray(int[] list) {
        for (int i=0; i< list.length; i++){
            System.out.print(list[i]+" ");
        }
    }


    public static void radixSort(int[] list){
        int largest = list[0];
        for (int i=1; i< list.length; i++){
            if (list[i] > largest){
                largest = list[i];
            }
        }

        int digits = 0;
        do {
            digits++;
            largest= largest/10;
        }while (largest != 0);

        ArrayList<ArrayList<Integer>> lists = getBuckets();
        int mod = 10;
        int divisor = 1;

        for (int i=0; i<digits; i++){

            for (int k=0; k< list.length; k++){
                lists.get((list[k]%mod)/divisor).add(list[k]);
            }

            int count = 0;
            for (int l=0; l< lists.size(); l++){
                for (int q=0; q<lists.get(l).size(); q++){
                    list[count++] = lists.get(l).get(q);
                }
            }

            divisor*=10;
            mod*=10;

            lists = getBuckets();
        }




    }

    public static ArrayList<ArrayList<Integer>> getBuckets(){
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i=0; i<10; i++){
            lists.add(new ArrayList<>());
        }
        return lists;
    }

}
