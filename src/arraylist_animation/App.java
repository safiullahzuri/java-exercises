package arraylist_animation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ArrayListVisualizer arrayListVisualizer = new ArrayListVisualizer();
        Scene scene = new Scene(arrayListVisualizer);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
