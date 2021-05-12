package threads_review.exercises;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class GenericParallelMergeSort<E extends Comparable<E>> {


    public static void main(String[] args) {
        Integer[] list = new Integer[256];
        for (int i=0; i<list.length; i++){
            list[i] = (int)(Math.random()*1000);
        }

        GenericParallelMergeSort<Integer> genericParallelMergeSort = new GenericParallelMergeSort<>();
        genericParallelMergeSort.parallelMergeSort(list);

        for (int i=0; i<list.length; i++){
            System.out.print(list[i]+" ");
            if (i%20 == 0 && i>0){
                System.out.println();
            }
        }
    }


    public void parallelMergeSort(E[] list){
        RecursiveAction mainTask = new MergeSortTask(list);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }




    private class MergeSortTask extends RecursiveAction{
        private final int THRESHOLD = 500;
        private E[] list;
        Ex13<E> ex13 = new Ex13<>();

        public MergeSortTask(E[] list){
            this.list = list;
        }

        @Override
        protected void compute() {
            if (list.length < THRESHOLD){
                Arrays.sort(list);
            }else {
                E[] firstHalf = (E[]) new Object[list.length/2];
                System.arraycopy(list, 0, firstHalf,0, list.length/2);

                invokeAll(new MergeSortTask(firstHalf));
                E[] secondHalf = (E[]) new Object[list.length-list.length/2];
                System.arraycopy(list, list.length/2, secondHalf, 0, secondHalf.length);

                //invokeAll(new MergeSortTask(firstHalf), new MergeSortTask(secondHalf));
                invokeAll(new MergeSortTask(secondHalf));

                ex13.merge(firstHalf, secondHalf, list);
            }

        }
    }


}
