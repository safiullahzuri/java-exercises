package threads_review.exercises;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Ex17 {

    public static void main(String[] args) {
        double[][] m1 = {
                {0, 3},
                {4, 5},
                {1, 8}
        };
        //m1[3][2]
        double[][] m2 = {
                {1, 3, 5, 4},
                {2, 1, 0, 7}
        };

        double[][] result = parallelMultiplication(m1, m2);


        for (int i=0; i< result.length; i++){
            System.out.println("{");
            for (int j=0; j<result[i].length; j++){
                System.out.print((int)result[i][j]+" ");
            }
            System.out.print("}");
        }


    }


    public static double[][] parallelMultiplication(double[][] a, double[][] b){
        double[][] result = new double[a.length][b[0].length];
        RecursiveAction task = new MultiplicationTask(a, b, result);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);
        return result;
    }


    private static class MultiplicationTask extends RecursiveAction{
        double[][] a;
        double[][] b;
        double[][] result;

        public MultiplicationTask(double[][] a, double[][] b, double[][] result){
            this.a = a;
            this.b = b;
            this.result = result;
        }


        @Override
        protected void compute() {
            ArrayList<RecursiveAction> tasks = new ArrayList<>();
            for (int i=0; i<a.length; i++){
                for (int j=0; j<b[0].length; j++){
                    tasks.add(new MultiplyAndFindCellResult(i, j));
                    //tasks[i+(j*i)] = new MultiplyAndFindCellResult(i, j);
                }
            }
            invokeAll(tasks);
        }

        public class MultiplyAndFindCellResult extends RecursiveAction{
            int i, j;

            public MultiplyAndFindCellResult(int i, int j){
                this.i = i;
                this.j = j;
            }

            @Override
            protected void compute() {
                result[i][j] = 0;
                for (int x=0; x<a[i].length; x++){
                    result[i][j] +=
                            a[i][x] * b[x][j];
                }
            }
        }

    }
}
