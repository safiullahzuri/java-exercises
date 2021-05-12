package Ex22;

public class E22_20 {
    public static void main(String[] args) {
        Integer[][] grid = {
                {9, 0, 6, 0, 7, 0, 4, 0, 3},
                {0, 0, 0, 4, 0, 0, 2, 0, 0},
                {0, 7, 0, 0, 2, 3, 0, 1, 0},
                {5, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 4, 0, 2, 0, 8, 0, 6, 0},
                {0, 0, 3, 0, 0, 0, 0, 0, 5},
                {0, 3, 0, 7, 0, 0, 0, 5, 0},
                {0, 0, 7, 0, 0, 5, 0, 0, 0},
                {4, 0, 5, 0, 1, 0, 7, 0, 8}
        };

        SudokuSolver s = new SudokuSolver(grid);

        int count = 1;
        for (Integer[][] solution: s.getSolutions()) {
            System.out.println("Solution " + count++);
            printGrid(solution);
            System.out.println();
        }
    }

    private static void printGrid(Integer[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}