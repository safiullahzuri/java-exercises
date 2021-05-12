package Ex22;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class HistogramGUI extends BorderPane {
    private Text tMessage;
    private TextField tfKey;
    private HistogramPane hpPane;
    private Button btStep;

    public HistogramGUI() {
        drawGUI();
    }

    private void drawGUI() {
        tMessage = new Text();
        hpPane = new HistogramPane();
        Text tKey = new Text("Key:");
        tfKey = new TextField();
        btStep = new Button("Step");
        Button btReset = new Button("Reset");

        btStep.setOnAction(e -> step());
        btReset.setOnAction(e -> reset());

        HBox hbBottom = new HBox(10);
        hbBottom.getChildren().addAll(tKey, tfKey, btStep, btReset);
        hbBottom.setAlignment(Pos.CENTER);

        setTop(tMessage);
        setCenter(hpPane);
        setBottom(hbBottom);
        setAlignment(tMessage, Pos.CENTER);
    }

    private void step() {
        if (!inputIsValid(tfKey.getText())) {
            tfKey.setText("");
            tMessage.setText("Invalid Input: enter an integer");
            return;
        }

        tfKey.setEditable(false);
        int key = Integer.parseInt(tfKey.getText());
        int result = hpPane.next(key);

        if (result >= 0) {
            tMessage.setText("The key is found in the array at index " + result);
            btStep.setDisable(true);
        } else if (result == -1) {
            tMessage.setText("The key is not in the array");
            btStep.setDisable(true);
        }
    }

    private void reset() {
        hpPane.setUp();
        tMessage.setText("");
        tfKey.setEditable(true);
        btStep.setDisable(false);
    }

    private boolean inputIsValid(String input) {
        if (input.length() < 1) { return false; }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (i == 0 && c == '-') { continue; }
            if (!Character.isDigit(c)) { return false; }
        }
        return true;
    }
}