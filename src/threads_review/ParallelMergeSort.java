package threads_review;

import Ex23.MergeSortDemo;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort {

    public static void main(String[] args) {
        final int SIZE = 7000000;
        int[] list = new int[SIZE];

        for (int i=0; i<list.length; i++){
            list[i] = (int)(Math.random()*1000000);
        }
        long startTime = System.currentTimeMillis();
        parallelMergeSort(list);
        long endTime = System.currentTimeMillis();
        System.out.println("\nProcessors:  "+Runtime.getRuntime().availableProcessors());
        System.out.println("Time: "+(endTime-startTime));
    }

    public static void parallelMergeSort(int[] list){
        RecursiveAction mainTask = new SortTask(list);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }

    private static class SortTask extends RecursiveAction{
        private int[] list;
        private final int THRESHOLD = 500;

        SortTask(int[] list){
            this.list = list;
        }

        @Override
        protected void compute() {
            if (list.length < THRESHOLD){
                Arrays.sort(list);
            }else{
                int[] firstHalf = new int[list.length/2];
                System.arraycopy(list, 0, firstHalf, 0, list.length/2);

                int secondHalfLength = list.length - list.length/2;
                int[] secondHalf = new int[secondHalfLength];
                System.arraycopy(list, list.length/2, secondHalf, 0, secondHalfLength);

                invokeAll(new SortTask(firstHalf), new SortTask(secondHalf));
                MergeSortDemo.merge(firstHalf, secondHalf, list);
            }
        }
    }

}
