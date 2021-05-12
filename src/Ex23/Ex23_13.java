package Ex23;

import java.util.ArrayList;
import java.util.Random;

import static Ex23.MergeSortDemo.printArray;


public class Ex23_13 {


    public static void main(String[] args) {
        //int[] list = {2,1,5,564,232,12,67,423};
        answerQuestion();
        Random random = new Random();
        //list = random.ints(1000,1,100000 ).toArray();
        //quickSort(list);
        //printArray(list);
        //insertionSort(list);
        //printArray(list);
    }

    private static void answerQuestion(){
        Random random = new Random();
        int[] sixtyThousands = random.ints(60000, 1, 100000).toArray();
        int[] _120000 = random.ints(120000,1,120000).toArray();
        int[] _180000 = random.ints(180000,1,180000).toArray();
        int[] _240000 = random.ints(240000,1,240000).toArray();
        int[] _300000 = random.ints(300000,1,300000).toArray();
        int[] _360000 = random.ints(360000,1,360000).toArray();

        System.out.printf("Array Size\t\t Selection Sort\t\t Bubble Sort\t\t Insertion Sort\t\t Merge Sort\t\t Heap Sort\t\t Radix Sort\t\t \n");

        System.out.printf("60,000    \t\t %d     \t\t\t %d \t\t\t\t\t\t%d \t\t\t\t\t\t %d\t\t\t\t %d\t\t\t %d\n", selectionSort(sixtyThousands, true), bubbleSort(sixtyThousands, true), insertionSort(sixtyThousands, true), mergeSort(sixtyThousands, true),  heapSort(sixtyThousands, true), radixSort(sixtyThousands, true));
        System.out.printf("120,000    \t\t %d     \t\t\t %d \t\t\t\t\t\t%d \t\t\t\t\t\t %d\t\t\t\t %d\t\t\t %d\n", selectionSort(_120000, true), bubbleSort(_120000, true), insertionSort(_120000, true), mergeSort(_120000, true),  heapSort(_120000, true), radixSort(_120000, true));
        System.out.printf("180,000    \t\t %d     \t\t\t %d \t\t\t\t\t\t%d \t\t\t\t\t\t %d\t\t\t\t %d\t\t\t %d\n", selectionSort(_180000, true), bubbleSort(_180000, true), insertionSort(_180000, true), mergeSort(_180000, true),  heapSort(_180000, true), radixSort(_180000, true));
        System.out.printf("240,000    \t\t %d     \t\t\t %d \t\t\t\t\t\t%d \t\t\t\t\t\t %d\t\t\t\t %d\t\t\t %d\n", selectionSort(_240000, true), bubbleSort(_240000, true), insertionSort(_240000, true), mergeSort(_240000, true),  heapSort(_240000, true), radixSort(_240000, true));
        System.out.printf("300,000    \t\t %d     \t\t\t %d \t\t\t\t\t\t%d \t\t\t\t\t\t %d\t\t\t\t %d\t\t\t %d\n", selectionSort(_300000, true), bubbleSort(_300000, true), insertionSort(_300000, true), mergeSort(_300000, true),  heapSort(_300000, true), radixSort(_300000, true));
        System.out.printf("360,000    \t\t %d     \t\t\t %d \t\t\t\t\t\t%d \t\t\t\t\t\t %d\t\t\t\t %d\t\t\t %d\n", selectionSort(_360000, true), bubbleSort(_360000, true), insertionSort(_360000, true), mergeSort(_360000, true),  heapSort(_360000, true), radixSort(_360000, true));

    }

    public static long selectionSort(int[] list, boolean timing){
        long start = System.currentTimeMillis();
        selectionSort(list);
        long end = System.currentTimeMillis();
        return (end-start);
    }

    public static long bubbleSort(int[] list, boolean timing){
        long start = System.currentTimeMillis();
        bubbleSort(list);
        long end = System.currentTimeMillis();
        return (end-start);
    }

    public static long insertionSort(int[] list, boolean timing){
        long start = System.currentTimeMillis();
        insertionSort(list);
        long end = System.currentTimeMillis();
        return (end-start);
    }

    public static long quickSort(int[] list, boolean timing){
        long start = System.currentTimeMillis();
        quickSort(list);
        long end = System.currentTimeMillis();
        return (end-start);
    }

    public static long heapSort(int[] list, boolean timing){
        long start = System.currentTimeMillis();
        heapSort(list);
        long end = System.currentTimeMillis();
        return (end-start);
    }

    public static long radixSort(int[] list, boolean timing){
        long start = System.currentTimeMillis();
        radixSort(list);
        long end = System.currentTimeMillis();
        return (end-start);
    }

    public static long mergeSort(int[] list, boolean timing){
        long start = System.currentTimeMillis();
        mergeSort(list);
        long end = System.currentTimeMillis();
        return (end-start);
    }

    public static void insertionSort(int[] list){

        for (int i=0; i< list.length; i++){
            int k;
            int currentElement = list[i];
            for (k=i-1; k>=0 && list[k]>currentElement; k--){
                list[k+1] = list[k];
            }
            list[k+1] = currentElement;
        }

    }


    //selection sort
    public static void selectionSort(int[] list){

        for (int i=0; i< list.length; i++){

            int currentSmallest = list[i];
            int smallestIndex = i;
            for (int j=i+1; j< list.length; j++){
                if (currentSmallest > list[j]){
                    currentSmallest = list[j];
                    smallestIndex = j;
                }
            }
            if (smallestIndex != i){
                int temp = list[i];
                list[i] = list[smallestIndex];
                list[smallestIndex] = temp;
            }
        }
    }



    //bubble sort
    public static void bubbleSort(int[] list){
        boolean needNextPass = true;

        for (int i=1; i< list.length && needNextPass; i++){

            needNextPass = false;
            for (int j=0; j< list.length-i; j++){
                if (list[j] > list[j+1]){
                    int temp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = temp;
                    needNextPass = true;
                }
            }

        }
    }

    //merge sort


    public static void mergeSort(int[] list){
        if (list.length > 1){
            int[] firstHalf = new int[list.length/2];
            System.arraycopy(list, 0, firstHalf, 0, list.length/2);
            mergeSort(firstHalf);

            int secondHalfLength = list.length - list.length/2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length/2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            merge(firstHalf, secondHalf, list);
        }
    }

    public static void merge(int[] firstHalf, int[] secondHalf, int[] temp){
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;


        while (current1 < firstHalf.length && current2 < secondHalf.length){

            if (firstHalf[current1] < secondHalf[current2]){
                temp[current3++] = firstHalf[current1++];
            }else{
                temp[current3++] = secondHalf[current2++];
            }
        }

        while (current1 < firstHalf.length){
            temp[current3++] = firstHalf[current1++];
        }

        while (current2 < secondHalf.length){
            temp[current3++] = secondHalf[current2++];
        }


    }




    //quick sort
    public static void quickSort(int[] list){
        quickSort(list, 0, list.length-1);
    }

    public static void quickSort(int[] list, int first, int last){
        if (last > first){
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex-1);
            quickSort(list, pivotIndex+1, last);
        }
    }

    public static int partition(int[] list, int first, int last){
        int pivot = list[first];

        int low = first+1;
        int high = last;

        while (high>low){

            while (low <= high && list[low] <= pivot){
                low++;
            }
            /*
            pivot = 10
            high = 21,23,234,2
             */
            while (low <= high && list[high] > pivot){
                high--;
            }

            if (high > low){
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        if (pivot > list[high]){
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }else{
            return first;
        }
    }




    //heap sort

    private static class MyHeap{
        ArrayList<Integer> list = new ArrayList<>();
        public MyHeap(){}

        public MyHeap(int[] list){
            for (int a: list){
                add(a);
            }
        }

        public void add(int a){
            list.add(a);
            int currentIndex = list.size()-1;

            while (currentIndex > 0){
                int parentIndex = (currentIndex-1)/2;
                if (list.get(currentIndex) > list.get(parentIndex)){
                    int temp  = list.get(currentIndex);
                    list.set(currentIndex, list.get(parentIndex));
                    list.set(parentIndex, temp);
                }else{
                    break;
                }
                currentIndex = parentIndex;
            }
        }

        public int remove(){
            if (list.size() == 0)return Integer.MIN_VALUE;
            int removedElement = list.get(0);
            list.set(0, list.get(list.size()-1));
            list.remove(list.size()-1);

            int currentIndex = 0;
            while (currentIndex < list.size()){
                int leftChildIndex = 2*currentIndex+1;
                int rightChildIndex = 2*currentIndex+2;

                if (leftChildIndex >= list.size())break;

                int maxIndex = leftChildIndex;

                if (rightChildIndex < list.size()){
                    if (list.get(maxIndex) < list.get(rightChildIndex)){
                        maxIndex = rightChildIndex;
                    }
                }

                if (list.get(maxIndex) > list.get(currentIndex)){
                    int temp = list.get(currentIndex);
                    list.set(currentIndex, list.get(maxIndex));
                    list.set(maxIndex, temp);
                    currentIndex = maxIndex;
                }else{
                    break;
                }
            }
            return removedElement;
        }
    }


    public static void heapSort(int[] list){
        MyHeap myHeap = new MyHeap(list);
        for (int i=list.length-1; i>=0; i--){
            list[i] = myHeap.remove();
        }
    }


    //radix sort

    public static void radixSort(int[] list){
        //find the largest element within the list
        int largest = list[0];
        for (int i=0; i<list.length; i++){
            if (list[i] > largest){
                largest = list[i];
            }
        }

        //find max digits
        int digits = 0;
        do{
            digits++;
            largest = largest/10;
        }while (largest != 0);

        ArrayList<ArrayList<Integer>> buckets = getBuckets();
        int mod = 10;
        int divisor = 1;

        for (int i=0; i<digits; i++){
        //for each digit do the following

            //for each element in the list, put them inside the appropriate bucket
            for (int j=0; j<list.length; j++){
                buckets.get((list[j]%mod)/divisor).add(list[j]);
            }
            int count = 0;
            for (int p=0; p< buckets.size(); p++){
                for (int q=0; q<buckets.get(p).size(); q++){
                    list[count++] = buckets.get(p).get(q);
                }
            }
            mod*=10;
            divisor*=10;

            buckets = getBuckets();
        }

    }

    public static ArrayList<ArrayList<Integer>> getBuckets(){
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i=0; i<10; i++){
            lists.add(new ArrayList<Integer>());
        }
        return lists;
    }
}
