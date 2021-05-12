package backtracking;

import com.sun.org.apache.regexp.internal.RE;

public class KnightTour {

    public static void main(String[] args) {
        KnightTour knightTour = new KnightTour();
        knightTour.solveTour(4,4);
    }

    final int BOARD_SIZE = 8;
    int[][] chessBoard;

   // int[] xMoves = {2,1,1,2,-2,-1,-1,-2};
    //int[] yMoves = {1,2,-2,-1,-1,-2,2,1};

    int[] xMoves = {2,1,-2,-1,-2,-1,1,2};
    int[] yMoves = {1, 2, -1, -2, 1,2,-2, -1};

    KnightTour(){
        initializeBoard();
    }

    private void initializeBoard(){
        chessBoard = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i=0; i<BOARD_SIZE; i++){
            for (int j=0; j<BOARD_SIZE;j++){
                chessBoard[i][j] = Integer.MIN_VALUE;
            }
        }
    }

    public void solveTour(int row, int col){
        chessBoard[row][col] = 1;
        if (solve(2, row, col)){
            printSolution();
        }else {
            System.out.println("No feasible solution");
        }
    }

    private boolean solve(int stepCount, int row, int col){
        if (stepCount == BOARD_SIZE*BOARD_SIZE+1){
            return true;
        }

        for (int i=0; i<8; i++){

            int nextX = xMoves[i] + row;
            int nextY = yMoves[i] + col;

            if (isAvailable(nextX, nextY)){
                //check further
                chessBoard[nextX][nextY] = stepCount;
                if (solve(stepCount+1, nextX, nextY)){
                    return true;
                }else{
                    chessBoard[nextX][nextY] = Integer.MIN_VALUE;
                }
            }

        }
        return false;

    }
    private boolean isAvailable(int row, int col){
        if (row<0 || row>=8) return false;
        if (col<0 || col >=8) return false;
        if (chessBoard[row][col] != Integer.MIN_VALUE) return false;

        return true;
    }


    private void printSolution(){
        for (int i=0; i<chessBoard.length; i++){
            for (int j=0; j<chessBoard[i].length; j++){
                System.out.print(chessBoard[i][j]+" ");
            }
            System.out.println();
        }
    }



}
