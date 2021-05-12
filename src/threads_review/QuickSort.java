package threads_review;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuickSort {


    public static void main(String[] args) {
        int N = 9000000;
        int[] list = new int[N];

        for (int i=0; i<list.length; i++){
            list[i] = (int)(Math.random()*9000000);
        }
        long startTime = System.currentTimeMillis();
        quickSort(list);
        long endTime = System.currentTimeMillis();

        System.out.println("Time taken sequentially: "+(endTime-startTime));

        int[] list2 = new int[N];

        for (int i=0; i<list2.length; i++){
            list2[i] = (int)(Math.random()*9000000);
        }
        startTime = System.currentTimeMillis();
        parallelQuickSort(list2);
        endTime = System.currentTimeMillis();

        System.out.println("Time taken parallel: "+(endTime-startTime));
        System.out.println("Processors: "+Runtime.getRuntime().availableProcessors());
    }

    public static void quickSort(int[] list){
        quickSort(list, 0, list.length-1);
    }

    public static void quickSort(int[] list, int first, int last){
        if (last > first){
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex);
            quickSort(list, pivotIndex+1, last);
        }
    }


    public static void parallelQuickSort(int[] list){
        QuickSortTask task = new QuickSortTask(list, 0, list.length-1);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);
    }

    public static class QuickSortTask extends RecursiveAction{

        private int[] list;
        private int first;
        private int last;

        public QuickSortTask(int[] list, int first, int last){
            this.list = list;
            this.first = first;
            this.last = last;
        }

        @Override
        protected void compute() {
            if (last > first){
                int partitionIndex = partition(list, first, last);
                invokeAll(new QuickSortTask(list, first, partitionIndex),
                        new QuickSortTask(list, partitionIndex+1, last));
            }
        }
    }


    public static int partition(int[] list, int first, int last){
        int pivot = list[first];
        int i = first+1;
        int j = last;

        while (j > i){
            //go forward until you find an element greater than pivot
            while (i <= j && list[i] <= pivot){
                i++;
            }
            //go backward until you find an element less than pivot
            while (i<=j && list[j] > pivot){
                j--;
            }
            //at this point, swap list[i] and list[j]
            if (j>i){
                int temp = list[j];
                list[j] = list[i];
                list[i] = temp;
            }
        }

        while (j>first && list[j] >= pivot){
            j--;
        }

        if (pivot > list[j]){
            list[first] = list[j];
            list[j] = pivot;
            return j;
        }else
            return first;

    }



}
