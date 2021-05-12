package Ex22_Difficult_Questions;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class HistogramEntireGui_v2 extends BorderPane {

    private Label resultLabel = new Label();
    private HistogramPane_v2 histogramPane_v2;
    private Label bottomLabel = new Label("Key (in double)");
    private TextField textField;
    private Button stepBtn, resetBtn;

    public HistogramEntireGui_v2(){
        histogramPane_v2 = new HistogramPane_v2();
        textField = new TextField();
        stepBtn = new Button("Step");
        resetBtn = new Button("Reset");

        setTop(resultLabel);
        setCenter(histogramPane_v2);
        setBottom(new HBox(bottomLabel, textField, stepBtn, resetBtn));

        stepBtn.setOnMouseClicked(e-> {
            step();
        });

        resetBtn.setOnMouseClicked(e->{
            reset();
        });
    }

    private void reset(){
        histogramPane_v2.setUp();
        textField.setText("");
        textField.setEditable(true);
        stepBtn.setDisable(false);
    }

    private void step(){
        if (!inputIsValid(textField.getText())){
            textField.setText("");
            resultLabel.setText("Invalid Input: enter an integer");
            return;
        }
        textField.setEditable(false);
        int key = Integer.parseInt(textField.getText());
        int result = histogramPane_v2.next(key);

        if (result >= 0){
            resultLabel.setText("The key is found in the array at index "+result);
            stepBtn.setDisable(true);
        }else if (result == -1){
            resultLabel.setText("The key is not in the array");
            stepBtn.setDisable(true);
        }
    }

    private boolean inputIsValid(String input){
        if (input.length() < 1){return false;}
        for(int i=0; i<input.length(); i++){
            char c=  input.charAt(i);
            if (i == 0 && c == '-'){continue;}
            if (!Character.isDigit(c)){return false;}
        }
        return true;
    }


}
