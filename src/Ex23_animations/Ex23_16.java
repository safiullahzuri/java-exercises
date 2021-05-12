package Ex23_animations;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ex23_16 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        BSHistogramPane ssHistogramPane = new BSHistogramPane();
        Scene scene = new Scene(ssHistogramPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
