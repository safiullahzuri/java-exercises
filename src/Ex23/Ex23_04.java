package Ex23;

import java.util.Arrays;

public class Ex23_04 {

    public static void main(String[] args) {
        int[] list = {13,2,1,5,7,4,32,23,65,21,112,11,435,23,123,53,75,33,112,4,7,8,9};
        quickSort(list, 0, list.length-1);
        printArray(list);
    }

    public static void quickSort(int[] list, int low, int high){
        if (low < high){
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex-1);
            quickSort(list, pivotIndex+1, high);
        }
    }

    static void printArray(int[] list){
        for (int a: list)
            System.out.print(a+" ");
        System.out.println();
    }

    public static int partition(int[] list, int low, int high){

        int[] potentialPivots = {list[low], list[high], list[list.length/2]};
        Arrays.sort(potentialPivots);

        if (potentialPivots[1] != list[low]){
            int pivotIndex;
            if (potentialPivots[1] == list[high]){
                pivotIndex = high;
            }else{
                pivotIndex = list.length/2;
            }
            int temp  = list[low];
            list[low] = potentialPivots[1];
            list[pivotIndex] = temp;
        }

        int i=low+1;
        int j=high;
        int pivot = list[low];

        /*
        median
        -- if list.size is odo
         */

        while (i < j){

            while (list[i] <= pivot && i<=j){
                i++;
            }

            while (list[j] > pivot && i<=j){
                j--;
            }

            if (i < j){
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }

        }
        int temp  = list[j];
        list[j] = list[low];
        list[low] = temp;
        return j;
    }
}
