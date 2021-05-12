package threads_review.exercises;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class NQueens extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    private int board[][];
    private int size;
//
//    public NQueens(int size){
//        this.size = size;
//        board = new int[size][size];
//        for (int i=0; i<board.length;i++){
//            for (int j=0; j<board[i].length; j++){
//                board[i][j] = 0;
//            }
//        }
//    }

    public void solveParallel(){
        board = new int[8][8];
        RecursiveTask<Boolean> task = new PlaceQueenTask(board,0);
        ForkJoinPool pool = new ForkJoinPool();
        boolean solved = pool.invoke(task);
        if (solved){
            printSolution();
        }else {
            System.out.println("no solution parallelly.");
        }
    }

    public void solve(){
        if (solveQueens(0)){
            printSolution();
        }else {
            System.out.println("No possible solution for this.");
        }
    }

    public boolean solveQueens(int row){
        if (row >= size) return true;

        for (int col=0; col<size; col++){
            if (isFeasible(row, col)){
                board[row][col] = 1;
                if (solveQueens(row+1)){
                    return true;
                }else{
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    private boolean isFeasible(int row, int col){
        //check for the same column
        for (int i=0; i<row; i++){
            if (board[i][col] == 1){
                return false;
            }
        }

        for (int i=row, j=col; i>=0 && j>=0; i--, j--){
            if (board[i][j] == 1){
                return false;
            }
        }

        for (int i=row, j=col; i>=0 && j<size; i--, j++){
            if (board[i][j] == 1){
                return false;
            }
        }

        return true;
    }

    private void printSolution(){
        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[i].length; j++){
                if (board[i][j] == 1){
                    System.out.print(" * ");
                }else{
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }
    static Rectangle[][] rectangles = new Rectangle[8][8];

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new FlowPane();
        pane.setPrefSize(330,320);

        for (int i=0; i<rectangles.length; i++){
            for (int j=0; j<rectangles[i].length; j++){
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setHeight(40);
                rectangle.setWidth(40);
                rectangles[i][j] = rectangle;

                pane.getChildren().add(rectangle);
            }
        }
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
        solveParallel();
    }

    private static void paintTheBoard(int[][] board){
        for (int i=0; i<rectangles.length; i++){
            for (int j=0; j<rectangles[i].length; j++){

                    rectangles[i][j].setFill(Color.WHITE);

            }
        }

        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[i].length; j++){
                if (board[i][j] == 1){
                    rectangles[i][j].setFill(Color.RED);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class PlaceQueenTask extends RecursiveTask<Boolean> {

        private int[][] board;
        int row;
        int size;

        public PlaceQueenTask(int[][] board, int row){
            this.board = board;
            this.row = row;
            size = board.length;
        }


        @Override
        protected Boolean compute() {
            if (row >= size) return true;

            for (int col=0; col<size; col++){
                if (isFeasible(row, col)){
                    board[row][col] = 1;
                    PlaceQueenTask task = new PlaceQueenTask(board, row+1);
                    task.fork();
                    boolean queenPlaced = task.join();
                    paintTheBoard(board);
                    if (queenPlaced){
                        return true;
                    }else{
                        board[row][col] = 0;
                    }
                }
            }
            return false;
        }

        private boolean isFeasible(int row, int col){
            //check for the same column
            for (int i=0; i<row; i++){
                if (board[i][col] == 1){
                    return false;
                }
            }

            for (int i=row, j=col; i>=0 && j>=0; i--, j--){
                if (board[i][j] == 1){
                    return false;
                }
            }

            for (int i=row, j=col; i>=0 && j<size; i--, j++){
                if (board[i][j] == 1){
                    return false;
                }
            }

            return true;
        }
    }






}
