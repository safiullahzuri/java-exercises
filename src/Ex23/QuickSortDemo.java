package Ex23;

public class QuickSortDemo {

    public static void main(String[] args) {
        int[] list = {13,2,1,5,7,4,32,23,65,21,112,11,435,23,123,53,75,33,112,4,7,8,9};
        quickSort(list);
        printArray(list);
    }

    public static void quickSort(int[] list){
        quickSort(list, 0, list.length-1);
    }

    public static void quickSort(int[] list, int low, int high){
        if (low < high){
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex-1);
            quickSort(list, pivotIndex+1, high);
        }
    }

    private static int partition(int[] list, int low, int high){
        int i=low+1;
        int j=high;
        int pivot = list[low];

        while (i<j){
            while (list[i] <= pivot && low<=high){
                i++;
            }
            while (list[j] > pivot && low<=high){
                j--;
            }
            if (i < j){
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        }




        int temp = list[low];
        list[low] = list[j];
        list[j] = temp;
        System.out.println("printing from inside the partition function:");
        printArray(list);
        System.out.println("Partitioning around "+list[j]);
        return j;
    }


    static void printArray(int[] list){
        for (int a: list)
            System.out.print(a+" ");
        System.out.println();
    }

}
