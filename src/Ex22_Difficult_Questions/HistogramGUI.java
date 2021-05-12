package Ex22_Difficult_Questions;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class HistogramGUI extends BorderPane {

    Text tMessage;
    TextField tfKey;
    HistogramPane3 histogramPane3;
    Button btnStep;

    public HistogramGUI(){
        drawGUI();
    }

    private void drawGUI(){
        tMessage = new Text();
        histogramPane3 = new HistogramPane3();
        Text tKey = new Text("Key:");
        tfKey = new TextField();
        btnStep = new Button("Step");
        Button btnReset = new Button("Reset");

        btnStep.setOnAction(e-> step());
        btnReset.setOnAction(e-> reset());

        HBox hbBottom = new HBox(10);
        hbBottom.getChildren().addAll(tKey, tfKey, btnStep, btnReset);
        hbBottom.setAlignment(Pos.CENTER);
        setTop(tMessage);
        setCenter(histogramPane3);
        setBottom(hbBottom);
        setAlignment(tMessage, Pos.CENTER);

    }

    private void step(){
        if (!inputIsValid(tfKey.getText())){
            tfKey.setText("");
            tMessage.setText("Invalid Input: enter an integer");
            return;
        }
        tfKey.setEditable(false);
        int key = Integer.parseInt(tfKey.getText());
        int result = histogramPane3.next(key);

        if (result >= 0){
            tMessage.setText("The key is found in the array at index "+result);
            btnStep.setDisable(true);
        }else if (result == -1){
            tMessage.setText("The key is not in the array.");
            btnStep.setDisable(true);
        }
    }

    private void reset(){
        histogramPane3.setup();
        tMessage.setText("");
        tfKey.setEditable(true);
        btnStep.setDisable(false);
    }

    private boolean inputIsValid(String input){
        if (input.length() < 1){
            return false;
        }
        for (int i=0; i<input.length(); i++){
            char c = input.charAt(i);
            if (i == 0 && c== '-'){
                continue;
            }
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }


}
