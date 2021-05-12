package stack_animation;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class StackVisualizer extends BorderPane {

    public StackVisualizer(){
        drawVisualizer();
    }

    private void drawVisualizer(){
        StackPane stackPane = new StackPane();
        setCenter(stackPane);

        Text text = new Text("Enter a value: ");
        TextField textField = new TextField();

        Button btnPush = new Button("Push");
        Button btnPop = new Button("Pop");
        Button btnPeek = new Button("Peek");

        btnPush.setOnAction(e->{
            if (textField.getText().isEmpty())return;
            int value = Integer.parseInt(textField.getText());
            stackPane.push(value);
        });

        btnPop.setOnAction(e->{
            if (textField.getText().isEmpty())return;
            int value = Integer.parseInt(textField.getText());
            stackPane.pop(value);
        });

        btnPeek.setOnAction(e->{
            stackPane.peek();
        });

        HBox hbControls = new HBox();
        hbControls.getChildren().addAll(text, textField, btnPush, btnPop, btnPeek);

        setBottom(hbControls);

    }




}
