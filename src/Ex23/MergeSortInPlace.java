package Ex23;

import static Ex23.MergeSortDemo.printArray;

public class MergeSortInPlace {

    public static void main(String[] args) {
        int[] list = {1,2,5,23,12,43,18,231,19};
        mergeSort(list, 0, list.length-1);
        printArray(list);
    }

    public static void mergeSort(int[] list, int low, int high){
        if (low < high){
            int mid = (low+high)/2;
            mergeSort(list, low, mid);
            mergeSort(list, mid+1, high);

            merge2(list,low, mid, high);
        }
    }

    public static void merge(int[] list,int low, int mid, int high){

        int[] temp = new int[high-low+1];

        int counter1 =low;
        int counter2 =mid+1;
        int counter3 =0;
        //first list is from low to mid
        //second list is from mid+1 to high
        while (counter1 <= mid && counter2 <= high){
            if (list[counter1] < list[counter2]){
                temp[counter3++] = list[counter1++];
            }else{
                temp[counter3++] = list[counter2++];
            }
        }
        while (counter1 <= mid){
            temp[counter3++] = list[counter1++];
        }
        while (counter2 <= high){
            temp[counter3++] = list[counter2++];
        }

        for (int i=low, j=0; i<=high; i++, j++){
            list[i] = temp[j];
        }

    }

    public static void merge2(int[] list, int low, int mid, int high){
        int counter1= low;
        int counter2 = mid+1;
        int counter3 = 0;

        int[] temp = new int[high-low+1];

        while (counter1 <= mid && counter2 <= high){
            if (list[counter1] < list[counter2]){
                temp[counter3++] = list[counter1++];
            }else{
                temp[counter3++] = list[counter2++];
            }
        }

        while (counter1 <= mid){
            temp[counter3++] = list[counter1++];
        }

        while (counter2 <= high){
            temp[counter3++] = list[counter2++];
        }
        for (int i=low, j=0; i<=high; i++, j++){
            list[i] = temp[j];
        }

    }



}
