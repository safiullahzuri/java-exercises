package graphs.exercises.day3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        NineTailBoard nineTailBoard = new NineTailBoard("TTHHTTTHHTTTHHTT");
        Scene scene = new Scene(nineTailBoard);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
