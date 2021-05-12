package Ex22;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EightQueens extends Application {

    static final int SIZE = 8;
    private int[] queens = {-1, -1, -1, -1, -1, -1, -1, -1};

    @Override
    public void start(Stage primaryStage) throws Exception {
        search();

        GridPane chessBoard = new GridPane();
        chessBoard.setAlignment(Pos.CENTER);

        Label[][] labels = new Label[SIZE][SIZE];
        for (int i=0; i<SIZE; i++){
            for (int j=0; j<SIZE; j++){
                chessBoard.add(labels[i][j] = new Label(), j, i);
                labels[i][j].setStyle("-fx-border-color:black");
                labels[i][j].setPrefSize(55,55);
            }
        }
        //Display Queens
        Image image = new Image("Ex22/queen.jpg");
        for (int i=0; i<SIZE; i++){
            labels[i][queens[i]].setGraphic(new ImageView(image));
        }
        Scene scene = new Scene(chessBoard, 55*SIZE, 55*SIZE);
        primaryStage.setTitle("Eight Queens");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private boolean search(){
        int k=0; //kth row
        while (k>=0 && k<SIZE){
            int j = findPosition(k);
            if (j < 0){
                queens[k] = -1;
                k--;
            }else{
                queens[k] = j;
                k++;
            }
        }
        if (k == -1){
            return false;
        }else{
            return true;
        }
    }

    public int findPosition(int k){
        int start = queens[k] + 1;
        for (int j= start; j<SIZE; j++){
            if (isValid(k, j)){
                return j;
            }
        }
        return -1;
    }

    public boolean isValid(int row, int col){

        for (int i=1; i<=row; i++){
            if (queens[row-1] == col || queens[row-i] == col-i || queens[row-i] == col+i){
                return false;
            }
        }
        return true;
    }

}
