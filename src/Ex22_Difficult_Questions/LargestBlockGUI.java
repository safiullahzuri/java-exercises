package Ex22_Difficult_Questions;

import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class LargestBlockGUI extends FlowPane {

    static final int WIDTH = 300;
    static final int HEIGHT = 300;
    static final int BLOCK_PER_ROW = 10;
    int[][] m = new int[10][10];
    TextField[][] textFields = new TextField[10][10];

    public LargestBlockGUI(){
        setPrefSize(WIDTH, HEIGHT);
        setUpTextBoxes();
    }

    private void setUpTextBoxes() {
        this.getChildren().clear();
        for (int i=0; i<BLOCK_PER_ROW; i++){
            for (int j=0; j<BLOCK_PER_ROW; j++){
                TextField tf = new TextField();
                tf.setPrefSize(WIDTH/10, HEIGHT/10);
                tf.setEditable(false);
                int rand = (int) Math.floor(Math.random()*10);
                tf.setText(rand%2 == 0 ? "0":"1");
                this.getChildren().add(tf);
                textFields[i][j] = tf;
                m[i][j] = Integer.parseInt(tf.getText());
            }
        }
    }


    public void refreshNumbers() {
        setUpTextBoxes();
    }

    public void findLargestBlock() {
        int[][] aux = new int[10][10];

        for (int i=0; i<m.length; i++){
            aux[i][0] = m[i][0];
        }

        for (int j=0; j<m[0].length; j++){
            aux[0][j] = m[0][j];
        }

        for (int i=1; i<m.length; i++){
            for (int j=1; j<m[i].length; j++){
                if (m[i][j] == 1){
                    aux[i][j] = Math.min(aux[i][j-1], Math.min(aux[i-1][j-1], aux[i-1][j]))+1;
                }else{
                    aux[i][j] = 0;
                }
            }
        }


        int largest = -1;
        int x=0, y=0;



        for (int i=0; i<aux.length; i++){
            for (int j=0; j<aux[i].length; j++){
                if (aux[i][j] > largest){
                    largest = aux[i][j];
                    x= i;
                    y= j;
                }
            }
        }

        int startingX = x - (largest-1);
        int startingY = y - (largest-1);

//        for (int ax = startingX; ax<=largest+ax; ax++ ){
//            for(int ay = startingY; ay<=largest+ay; ay++){
//                textFields[ax][ay].setStyle("-fx-background-color:black;");
//            }
//        }

        for (int i=0; i<largest; i++){
            for (int j=0; j<largest; j++){
                textFields[i+startingX][j+startingY].setStyle("-fx-background-color:red;");
            }
        }



    }
}
