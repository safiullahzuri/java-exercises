package Ex23_animations;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Ex23_17 extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {

        Ex23_17Pane ex23_17Pane = new Ex23_17Pane();
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(ex23_17Pane);

        HBox hBox = new HBox(30);
        Button btnStep = new Button("Step");
        Button btnReset = new Button("Reset");

        btnStep.setOnAction(e->{
            ex23_17Pane.step();
        });

        btnReset.setOnAction(e->{
            ex23_17Pane.reset();
        });
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(btnStep, btnReset);

        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
