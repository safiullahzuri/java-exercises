package Ex22_Difficult_Questions;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Ex22_19 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        LargestBlockGUI largestBlockGUI = new LargestBlockGUI();
        borderPane.setCenter(largestBlockGUI);

        HBox hBox = new HBox(10);
        Button refreshBtn = new Button("Refresh");
        Button largestBlockBtn = new Button("Find Largest Block");
        hBox.getChildren().addAll(refreshBtn, largestBlockBtn);

        refreshBtn.setOnMouseClicked(e->{
            largestBlockGUI.refreshNumbers();
        });

        largestBlockBtn.setOnMouseClicked(e->{
            largestBlockGUI.findLargestBlock();
        });

        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
