package backtracking;

public class NQueens2 {

    final int numberOfQueens;
    int[][] chessBoard;

    public static void main(String[] args) {
        NQueens2 nQueens2 = new NQueens2(8);
        nQueens2.solve();
    }

    public NQueens2(int numberOfQueens) {
        this.numberOfQueens = numberOfQueens;
        chessBoard = new int[numberOfQueens][numberOfQueens];

        initArray();
    }

    private void initArray(){
        chessBoard = new int[numberOfQueens][numberOfQueens];
        for (int i=0; i< chessBoard.length; i++){
            for (int j=0; j<chessBoard[i].length; j++){
                chessBoard[i][j] = 0;
            }
        }
    }


    public void solve(){
        if (setQueens(0)){
            printSolution();
        }else {
            System.out.println("No feasible solution.");
        }
    }

    public boolean setQueens(int row){
        if (row == numberOfQueens){
            return true;
        }

        for (int col=0; col<numberOfQueens; col++){
            if (isCellAvailable(row, col)){
                chessBoard[row][col] = 1;
                if(setQueens(row+1)){
                    return true;
                }else{
                    chessBoard[row][col] = 0;
                }
            }
        }
        return false;
    }

    private boolean isCellAvailable(int row, int col){

        for (int i=0; i<row; i++){
            if (chessBoard[i][col] == 1){
                return false;
            }
        }

        for (int i=row, j=col; i>=0 && j>=0; i--, j--){
            if (chessBoard[i][j] == 1){
                return false;
            }
        }

        for (int i=row,j=col; i>=0 && j<numberOfQueens; i--, j++){
            if (chessBoard[i][j] == 1){
                return false;
            }
        }
        return true;
    }

    private void printSolution(){
        for (int i=0; i<chessBoard.length; i++){
            for (int j=0; j<chessBoard[i].length; j++){
                if (chessBoard[i][j] == 1){
                    System.out.print(" * ");
                }else{
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

}
