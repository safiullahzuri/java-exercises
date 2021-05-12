package graphs.exercises.day5;

import com.sun.org.apache.regexp.internal.RE;

public class KnightTour {

    public static void main(String[] args) {
        KnightTour knightTour = new KnightTour();
        knightTour.solveTour(4,4);
    }

    int[][] chessBoard;
    final int BOARD_SIZE = 8;

    int[] xMoves = {2,1,-2,-1,-2,-1,1,2};
    int[] yMoves = {1, 2, -1, -2, 1,2,-2, -1};

    public KnightTour(){

        initChessBoard();



    }

    private void initChessBoard(){
        chessBoard = new int[BOARD_SIZE][BOARD_SIZE];

        for (int i=0; i<BOARD_SIZE; i++){
            for (int j=0; j<BOARD_SIZE; j++){
                chessBoard[i][j] = Integer.MIN_VALUE;
            }
        }
    }

    public void solveTour(int row, int column){
        chessBoard[row][column] = 1;
        if (solve(2, row, column)){
            printSolution();
        }else{
            System.out.println("No feasible solution.");
        }
    }

    public boolean solve(int stepCount, int x, int y){
        if (stepCount == BOARD_SIZE*BOARD_SIZE+1){
            return true;
        }
        for (int i=0; i<8; i++){
            //indicating eight moves
            int nextX = x+ xMoves[i];
            int nextY = y + yMoves[i];

            if (isValidStep(nextX, nextY)){
                chessBoard[nextX][nextY] = stepCount;

                if (solve(stepCount+1, nextX,nextY)){
                    return true;
                }
                chessBoard[nextX][nextY] = Integer.MIN_VALUE;
            }
        }
        return false;
    }

    private boolean isValidStep(int x, int y){
        if (x < 0 || x>=8) return false;
        if (y < 0 || y>= 8) return false;
        if (chessBoard[x][y] != Integer.MIN_VALUE) return false;
        return true;
    }

    private void printSolution(){
        for (int i=0; i<chessBoard.length; i++){
            for (int j=0; j<chessBoard[i].length; j++){
                System.out.print(chessBoard[i][j]+", ");
            }
            System.out.println();
        }
    }

}
