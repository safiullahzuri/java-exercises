package heap_visualisation;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class E23_10 extends Application {
    @Override
    public void start(Stage primaryStage) {
        HeapVisualizer hv = new HeapVisualizer();

        Scene scene = new Scene(hv);
        primaryStage.setTitle("E23_10");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}