package Ex22;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class E22_16 extends Application {

    @Override
    public void start(Stage primaryStage) {
        HistogramGUI pane = new HistogramGUI();

        Scene scene = new Scene(pane);
        primaryStage.setTitle("E22_16");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}