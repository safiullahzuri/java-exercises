package quicksort_partition;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class PartitionVisualizer extends BorderPane {

    public PartitionVisualizer(){
        drawGUI();
    }

    private void drawGUI(){
        Text tMessage = new Text();
        PartitionPane partitionPane = new PartitionPane();
        partitionPane.setStyle("-fx-border-color:black");

        Button btnStep = new Button("Step");
        Button btnReset = new Button("Reset");

        btnStep.setOnAction(e-> {
            tMessage.setText(partitionPane.step() ? "FINISHED" : "");
        });

        btnReset.setOnAction(e-> {
            tMessage.setText("");
            partitionPane.reset();
        });

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(btnStep, btnReset);
        hBox.setAlignment(Pos.CENTER);

        setTop(tMessage);
        setCenter(partitionPane);
        setBottom(hBox);

        setAlignment(tMessage, Pos.CENTER);

    }

}
