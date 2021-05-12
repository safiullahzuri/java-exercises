package Ex23;

import java.util.ArrayList;

public class E23_12 {

    public static void main(String[] args) {
        int n = 20;
        int[] list = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = (int)(Math.random() * n);
        }

        myRadixSort(list);
        displayList(list);
    }


    public static void myRadixSort(int[] list){
        int largest = list[0];
        for (int i=1; i< list.length; i++){
            if (list[i] > largest){
                largest = list[i];
            }
        }

        int digits = 0;
        do {
            largest = largest/10;
            digits++;
        }while (largest != 0);

        ArrayList<ArrayList<Integer>> buckets = getMyBuckets();

        int mod = 10;
        int divisor = 1;

        for (int i=0; i<digits; i++){


            for (int j=0; j < list.length; j++){
                buckets.get((list[j]%mod)/divisor).add(list[j]);
            }

            int count = 0;
            for (int p=0; p< buckets.size(); p++){
                for (int q = 0; q<buckets.get(p).size(); q++){
                    list[count++] = buckets.get(p).get(q);
                }
            }
            mod=mod*10;
            divisor=divisor*10;

            buckets = getMyBuckets();
        }


    }

    public static ArrayList<ArrayList<Integer>> getMyBuckets(){
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i=0; i<10; i++){
            lists.add(new ArrayList<>());
        }
        return lists;
    }




    public static void radixSort(int[] list){
        int largest = list[0];
        for (int i=1; i< list.length; i++){
            if (list[i] > largest){
                largest = list[i];
            }
        }
        int digits = 0;
        do{
            largest/=10;
            digits++;
        }while (largest!=0);

        ArrayList<ArrayList<Integer>> buckets = getBuckets();
        int m=10;
        int n=1;

        for (int i=0; i<digits; i++){
            for (int j=0; j< list.length; j++){
                buckets.get((list[j]%m) / n).add(list[j]);
            }
            int count = 0;
            for (int k=0; k< buckets.size(); k++){
                for (int p=0; p<buckets.get(k).size(); p++){
                    list[count++] = buckets.get(k).get(p);
                }
            }
            m*=10;
            n*=10;
            buckets = getBuckets();
        }


    }

    public static void displayList(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }


    public static ArrayList<ArrayList<Integer>> getBuckets(){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i=0; i<10; i++){
            list.add(new ArrayList<>());
        }
        return list;
    }

}
