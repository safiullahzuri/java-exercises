package ex24_12;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ex24_12 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        QueueVisualizer queueVisualizer = new QueueVisualizer();
        Scene scene = new Scene(queueVisualizer);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
