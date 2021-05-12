package threads_review.exercises;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Ex15 {


    public static void main(String[] args) {
        double[] list = {1,2,4,3,2};
        System.out.println(sum(list));
        System.out.println(parallelSum(list));
    }

    public static double sum(double[] list){
        return sum(list, 0, list.length-1);
    }

    public static double sum(double[] list, int low, int high){
        if (high == low){
            return list[low];
        }
        else if (high-low == 1){
            return list[low]+list[high];
        }else {
            int mid = (low+high)/2;
            double left = sum(list, low, mid);
            double right = sum(list, mid+1, high);

            return left+right;
        }
    }

    public static double parallelSum(double[] list){
        RecursiveTask<Double> task = new ParallelSumTask(list, 0, list.length-1);
        ForkJoinPool pool = new ForkJoinPool();

        return pool.invoke(task);

    }

    public static class ParallelSumTask extends RecursiveTask<Double>{
        double[] list;
        int low;
        int high;

        public ParallelSumTask(double[] list, int low, int high){
            this.list = list;
            this.low = low;
            this.high = high;
        }

        @Override
        protected Double compute() {
            System.out.println("method invoked");

            if (high == low){
                return list[low];
            }else if (high-low == 1){
                return list[low] + list[high];
            }else{
                int mid = (low+high)/2;
                RecursiveTask<Double> left = new ParallelSumTask(list, low, mid);
                RecursiveTask<Double> right = new ParallelSumTask(list, mid+1, high);
                left.fork();
                right.fork();

                return left.join() + right.join();
            }
        }
    }















}
