package threads_review;

public class SortFlashbacks {

    public static void main(String[] args) {
        int[] list = {2,4,6,3,123,65,43,23,9};
        selectionSort(list);
        printArray(list);
    }

    public static void selectionSort(int[] list){
        for (int i=0; i<list.length; i++){
            int smallestElement = list[i];
            int smallestIndex = i;

            for (int j=i; j<list.length; j++){
                if (list[j]<smallestElement){
                    smallestElement = list[j];
                    smallestIndex = j;
                }
            }
            int temp = list[i];
            list[i] = smallestElement;
            list[smallestIndex] = temp;
        }
    }


    public static void bubbleSort(int[] unsortedArray){
        boolean needNextPass = true;
        for (int pass=1; pass<unsortedArray.length && needNextPass; pass++){
            needNextPass = false;
            for (int i=0; i<unsortedArray.length-pass; i++){
                if (unsortedArray[i] > unsortedArray[i+1]){
                    swap(unsortedArray, i, i+1);
                    needNextPass = true;
                }
            }
        }
    }

    public static void insertionSort(int[] list){
        for (int i=1; i<list.length; i++){
            int currentElement = list[i];
            int k;

            for (k=i-1; k>=0 && list[k]>currentElement; k--){
                list[k+1] = list[k];
            }
            list[k+1] = currentElement;
        }
    }





    private static void swap(int[] array, int f, int s){
        int temp = array[f];
        array[f] = array[s];
        array[s] = temp;
    }

    private static void printArray(int[] array){
        for (int i=0; i<array.length; i++){
            System.out.print(array[i]+" ");
        }
    }

}
