package Ex23_animations;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MergeAnimationWholePane extends BorderPane {

    private Button btnStep, btnReset;

    public MergeAnimationWholePane(){
        setUpLayout();
    }

    private void setUpLayout(){
        btnStep = new Button("Step");
        btnReset = new Button("Reset");

        HBox hBox = new HBox(20,btnStep, btnReset);
        hBox.setAlignment(Pos.CENTER);

        MergeSortPane mergeSortPane = new MergeSortPane();

        setCenter(mergeSortPane);

        setBottom(hBox);

        btnStep.setOnAction(e->{
            mergeSortPane.step();
        });

        btnReset.setOnAction(e->{
            mergeSortPane.reset();
        });
    }

}
