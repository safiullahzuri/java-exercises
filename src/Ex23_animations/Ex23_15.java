package Ex23_animations;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ex23_15 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        SSHistogramPane ssHistogramPane = new SSHistogramPane();
        Scene scene = new Scene(ssHistogramPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
