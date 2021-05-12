package Ex23_animations;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class SSHistogramPane extends BorderPane {

    private Label resultLbl;
    private Histogram histogram;
    private Button btnStep, btnReset;

    public SSHistogramPane(){
        setUpLayout();


    }

    private void setUpLayout(){
        histogram = new Histogram();
        resultLbl = new Label();
        histogram = new Histogram();
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
