package Ex22;

import java.util.ArrayList;

public class SudokuSolver {
    private Integer[][] grid;
    private ArrayList<Integer[][]> solutions;

    public SudokuSolver(Integer[][] grid) throws IllegalArgumentException {
        this.grid = grid;
        solutions = solve(grid);
    }

    public ArrayList<Integer[][]> getSolutions() {
        return solutions;
    }

    private ArrayList<Integer[][]> solve(Integer[][] grid)
            throws IllegalArgumentException {
        if (!isValid(grid)) {
            throw new IllegalArgumentException("Invalid grid");
        }

        return search(grid);
    }

    private ArrayList<Integer[][]> search(Integer[][] grid) {
        Integer[][] freeCells = getFreeCellList(grid);
        ArrayList<Integer[][]> solutions = new ArrayList<>();
        if (freeCells.length == 0) {
            solutions.add(grid);
            return solutions;
        }

        int k = 0;
        while (true) {
            int i = freeCells[k][0];
            int j = freeCells[k][1];

            if (grid[i][j] == 0) {
                grid[i][j] = 1;
            }

            // "proceed" generally shadows the result of isValid(i, j, grid)
            boolean proceed = false;
            if (isValid(i, j, grid)) {
                proceed = true;
                if (k + 1 == freeCells.length) {
                    // except here, where we make "proceed" false to indicate that, after
                    // receiving a valid solution, we wish to pretend it was incorrect
                    // and backtrack to find other possible solutions
                    solutions.add(makeCopy(grid)); // make deep copy before adding
                    proceed = false;
                } else {
                    k++;
                }
            }

            // this conditional is decoupled from the isValid(i, j, grid) check
            // in order to allow for the situation where we've found one solution
            // but want to pretend it's "wrong" in order to find additional solutions
            if (!proceed) {
                if (grid[i][j] < 9) { // current space is less than 9
                    grid[i][j] = grid[i][j] + 1; // increment the space
                } else {
                    while (grid[i][j] == 9) {
                        // if we backtrack all the way to the first freeCell and the value
                        // is still 9, we have found all possible solutions
                        if (k == 0) {
                            return solutions;
                        }
                        // keep backtracking and resetting current and subsequent 9s
                        grid[i][j] = 0;
                        k--;
                        i = freeCells[k][0];
                        j = freeCells[k][1];
                    }
                    grid[i][j] = grid[i][j] + 1; // the increment the first non-9 value
                }
            }
        }
    }

    private static Integer[][] getFreeCellList(Integer[][] grid) {
        int numberOfFreeCells = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    numberOfFreeCells++;
                }
            }
        }

        Integer[][] freeCells = new Integer[numberOfFreeCells][2];
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    freeCells[count][0] = i;
                    freeCells[count][1] = j;
                    count++;
                }
            }
        }
        return freeCells;
    }

    private static boolean isValid(int i, int j, Integer[][] grid) {
        // check for incorrectness in column values in the row
        for (int col = 0; col < 9; col++) {
            if (col != j && grid[i][col] == grid[i][j]) {
                return false;
            }
        }

        // check for incorrectness in row values in the column
        for (int row = 0; row < 9; row++) {
            if (row != i && grid[row][j] == grid[i][j]) {
                return false;
            }
        }

        // check for incorrectness in the 3x3 square
        for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++) {
            for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++) {
                if (row != i && col != j && grid[row][col] == grid[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isValid(Integer[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] < 0 || grid[i][j] > 9 || // val is < 0 or > 9
                        (grid[i][j] != 0 && !isValid(i, j, grid))) { // placement invalid
                    return false;
                }
            }
        }
        return true;
    }

    private static Integer[][] makeCopy(Integer[][] grid) {
        Integer[][] copy = new Integer[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copy[i][j] = grid[i][j];
            }
        }
        return copy;
    }
}