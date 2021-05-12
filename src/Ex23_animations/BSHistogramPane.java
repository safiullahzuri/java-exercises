package Ex23_animations;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class BSHistogramPane extends BorderPane {


    private Label resultLbl;
    private BSHistogram histogram;
    private Button btnStep, btnReset;

    public BSHistogramPane(){
        setUpLayout();


    }

    private void setUpLayout(){
        resultLbl = new Label();
        histogram = new BSHistogram();
        btnStep = new Button("Step");
        btnReset=  new Button("Reset");

        setTop(resultLbl);

        setCenter(histogram);
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(btnStep, btnReset);
        setBottom(hBox);

        btnStep.setOnAction(e-> {
            histogram.step();
        });

        btnReset.setOnAction(e->{
            histogram.reset();
        });
    }

}
