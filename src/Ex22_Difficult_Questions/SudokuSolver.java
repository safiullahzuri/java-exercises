package Ex22_Difficult_Questions;

import java.util.ArrayList;

import static Ex22_Difficult_Questions.MaximumSizeSubMatrix.printArray;

public class SudokuSolver {

    private SudokuSolver(){

    }
    public SudokuSolver(int[][] grid){
        this.grid = grid;
    }

    static final int SIZE = 9;
    int[][] board;
    static final int EMPTY = 0;

    public ArrayList<int[][]> solutions = new ArrayList<>();

    public static int[][] grid = {
            {9,0,0,0,0,0,0,0,5},
            {0,0,2,0,0,0,0,0,0},
            {8,0,0,3,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,1,0,0,0},
            {7,0,0,0,0,0,0,0,0},
            {0,0,0,5,0,0,0,0,0},
            {0,0,0,0,0,0,8,0,9}
    };

    public static int[][] gridCopy = {
            {9,0,0,0,0,0,0,0,5},
            {0,0,0,0,0,0,0,0,0},
            {8,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    public static void main(String[] args) {
        SudokuSolver sudokuSolver = new SudokuSolver();
        sudokuSolver.solve();

        for (int i=1; i<sudokuSolver.solutions.size(); i++){
            if (sudokuSolver.solutions.get(i) == sudokuSolver.solutions.get(i-1)){
                System.out.println("There are equal solutions.");
            }
        }

        System.out.println(sudokuSolver.solutions.size()+" solutions");
    }

    private boolean noMoreOne(int[][] grid){
        for (int i=0; i<grid.length; i++){
            for (int j=0; j<grid[i].length; j++){
                if (grid[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solve(){
        for (int row=0; row<SIZE; row++){

            for (int col=0; col<SIZE; col++){

                if (grid[row][col] == EMPTY){

                    for (int number = 1; number<=SIZE; number++){

                        if (isOk(row, col, number)){
                            //System.out.printf("trying number=>%d for row=>%d, col=>%d \n", number, row, col);
                            grid[row][col] = number;

                            if (solve()){
                                    solutions.add(grid);
                                    System.out.println();
                                    printArray(grid);
                                    return true;
                            }else{
                                grid[row][col] = EMPTY;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isInRow(int row, int number){
        for (int i=0; i<SIZE; i++){
            if (grid[row][i] == number){
                return true;
            }
        }
        return false;
    }

    private boolean isInColumn(int column, int number){
        for (int i=0; i<SIZE; i++){
            if (grid[i][column] == number){
                return true;
            }
        }
        return false;
    }

    private boolean isInBox(int row, int column, int number){
        int r = row - row%3;
        int c = column - column%3;

        for (int i=r; i<r+3; i++){
            for (int j=c; j<c+3; j++){
                if (grid[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isOk(int row, int column, int number){
        return !isInRow(row, number) && !isInColumn(column, number) && !isInBox(row, column, number);
    }

}
