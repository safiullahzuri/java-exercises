package multiple_queens;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class BoardPane extends BorderPane {
    private Integer[] board;
    private Label[][] labels;
    private int solutionNumber;

    public BoardPane(Integer[] board, int solutionNumber){
        this.board = board;
        labels = new Label[board.length][board.length];
        this.solutionNumber = solutionNumber;
        drawGUI();
    }

    public Label[][] getLabels(){
        return labels;
    }

    private void drawGUI(){
        Text tSolution = new Text("Solution "+solutionNumber);
        GridPane boardGrid = new GridPane();
        boardGrid.setAlignment(Pos.CENTER);
        for (int row=0; row<board.length; row++){
            for (int col=0; col<board.length; col++){
                Label label = new Label();
                label.setPrefSize(55,55);
                label.setStyle("-fx-border-color:black;");
                labels[row][col] = label;
                boardGrid.add(label, row, col);
            }
        }
        for (int row=0; row<board.length; row++){
            labels[row][board[row]].setStyle("-fx-background-color:red;");
        }
        setTop(tSolution);
        setCenter(boardGrid);
        setAlignment(tSolution, Pos.CENTER);
    }
}
