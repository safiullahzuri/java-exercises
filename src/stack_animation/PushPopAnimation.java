package stack_animation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PushPopAnimation extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackVisualizer stackVisualizer = new StackVisualizer();
        Scene scene = new Scene(stackVisualizer);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
