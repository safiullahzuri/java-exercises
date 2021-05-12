package multiple_queens;

import java.util.ArrayList;
import java.util.Arrays;

public class Queens {
    private static final int SIZE = 8;
    private static Integer[] board = {-1,-1,-1,-1,-1,-1,-1,-1};
    private ArrayList<Integer[]> boards;

    public Queens(){
        boards = search();
    }

    public ArrayList<Integer[]> getBoards() {
        return boards;
    }

    private static ArrayList<Integer[]> search(){
        ArrayList<Integer[]> boards = new ArrayList<>();
        int row = 0;

        while (true){
            if (board[row] == -1){
                board[row] = 0;
            }

            boolean shouldReturn = false;
            if (isValid(row, board[row])){
                shouldReturn = true;
                if (row+1 == SIZE){
                    boards.add(Arrays.copyOf(board, board.length));
                    shouldReturn = false;
                }else{
                    row++;
                }
            }
            if (!shouldReturn){
                if (board[row] < SIZE){
                    board[row]++;
                }else{
                    while (!isValid(row, board[row])){
                        board[row] = -1;
                        row--;
                        if (row == -1){
                            return boards;
                        }
                    }
                    board[row]++;
                }
            }

        }
    }

    private static boolean isValid(int row, int col){
        if (col >= SIZE){return false;}
        for (int i=1; i<=row; i++){
            if (board[row-i] == col || board[row-i] == col-i || board[row-1]==col+i){
                return false;
            }
        }
        return true;
    }
}
