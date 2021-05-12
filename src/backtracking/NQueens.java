package backtracking;


public class NQueens {

    public static void main(String[] args) {
        NQueens nQueens = new NQueens(8);
        nQueens.solveNQueens();
    }

    int SIZE;
    int[][] chessBoard;

    public NQueens(int size){
        this.SIZE = size;
        initBoard();
    }

    private void initBoard(){
        chessBoard = new int[SIZE][SIZE];
        for (int i=0; i<SIZE; i++){
            for (int j=0;j<SIZE; j++){
                chessBoard[i][j] = 0;
            }
        }
    }

    private void solveNQueens(){
        if (solve(0)){
            printSolution();
        }else {
            System.out.println("No feasible solution");
        }
    }

    private boolean solve(int col){
        if (col == SIZE ){
            return true;
        }

        for (int row=0; row<8; row++){
            if (isAvailableCell(col, row)){
                chessBoard[row][col] = 1;
                if (solve(col+1)){
                    return true;
                }else{
                    chessBoard[row][col] = 0;
                }
            }
        }
        return false;
    }

    private boolean isAvailableCell(int column, int row){
        //check if we have any 1 in the current row or column
        for (int i=0; i< column; i++){
            if (chessBoard[row][i] == 1)
                return false;
        }

        for (int i=row, j=column; i>=0 && j>=0; i--, j--){
            if (chessBoard[i][j] == 1){
                return false;
            }
        }

        for (int i=row, j=column; i<chessBoard.length && j>=0; i++, j--){
            if (chessBoard[i][j] == 1)
                return false;
        }

        return true;
    }

    private void printSolution(){
        for (int i=0; i<chessBoard.length; i++){
            for (int j=0; j<chessBoard[i].length; j++){
                if (chessBoard[i][j] == 1){
                    System.out.print("* ");
                }else{
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }



}
